package se.mtm.gradle.tasks;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import org.gradle.api.DefaultTask;
import org.gradle.api.logging.Logger;
import org.gradle.api.tasks.TaskAction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class TriggerPuppetTask extends DefaultTask {

    @TaskAction
    public void triggerPuppet() throws IOException {
        Logger logger = getLogger();

        String host = "192.168.33.10";
        logger.lifecycle("Trigger puppet on " + host);

        String user = "testUser";
        String password = "testUsersPassword";

        List<String> executionLog = triggerPuppet(user, password, host);

        for (String line : executionLog) {
            logger.lifecycle(line);
        }
    }

    private List<String> triggerPuppet(String userName, String password, String host) throws IOException {
        Connection connection = null;
        try {
            connection = connectTo(host, userName, password);
            return triggerPuppet(connection);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    private Connection connectTo(String host, String userName, String password) throws IOException {
        Connection connection = new Connection(host);
        connection.connect();
        connection.authenticateWithPassword(userName, password);

        return connection;
    }

    private List<String> triggerPuppet(Connection connection) throws IOException {
        String command = "ls -la ";
        List<String> executionLog = new LinkedList<>();
        Session session = null;

        try {
            session = connection.openSession();
            session.execCommand(command);
            InputStream stdout = new StreamGobbler(session.getStdout());

            try (BufferedReader br = new BufferedReader(new InputStreamReader(stdout))) {
                String line = br.readLine();
                while (line != null) {
                    executionLog.add(line);
                    line = br.readLine();
                }
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return executionLog;
    }
}

