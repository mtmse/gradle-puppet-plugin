package se.mtm.gradle.tasks;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class SshExecutor {
    public static List<String> triggerPuppet(String user, String password, String host) throws IOException {
        validateParams(user, password, host);

        Connection connection = null;
        try {
            connection = connectTo(user, password, host);
            return executePuppet(connection);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    private static void validateParams(String user, String password, String host) {
        if (user == null || user.isEmpty()) {
            throw new PluginException("User must be set with '-Duser=userName'");
        }

        if (password == null || password.isEmpty()) {
            throw new PluginException("Password must be set with '-Dpassword=password'");
        }

        if (host == null || host.isEmpty()) {
            throw new PluginException("Host must be set with '-Dhost=hostName' or in the gradlePuppet configuration in the build script");
        }
    }

    private static Connection connectTo(String user, String password, String host) throws IOException {
        Connection connection = new Connection(host);
        connection.connect();
        connection.authenticateWithPassword(user, password);

        return connection;
    }

    private static List<String> executePuppet(Connection connection) throws IOException {
        String command = "sudo puppet agent -vt";
        List<String> executionLog = new LinkedList<>();
        Session session = null;

        try {
            session = connection.openSession();
            session.requestDumbPTY();
            session.execCommand(command);
            InputStream stdout = new StreamGobbler(session.getStdout());
            InputStream stderr = new StreamGobbler(session.getStderr());

            executionLog.add("stderr:");
            readStdErr(executionLog, stderr);
            executionLog.add("");

            executionLog.add("stdout:");
            readStdErr(executionLog, stdout);
            executionLog.add("");
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return executionLog;
    }

    private static void readStdErr(List<String> executionLog, InputStream stderr) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(stderr))) {
            String line = br.readLine();
            while (line != null) {
                executionLog.add(line);
                line = br.readLine();
            }
        }
    }
}
