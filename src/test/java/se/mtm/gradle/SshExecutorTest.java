package se.mtm.gradle;

import org.junit.Ignore;
import org.junit.Test;
import se.mtm.gradle.tasks.SshExecutor;

import java.util.List;

public class SshExecutorTest {

    @Test
    @Ignore
    public void trigger_remote_execution() throws Exception {
        String userName = ""; // set as you need when you want to test
        String password = "";
        String host = "";
        List<String> executionLog = SshExecutor.triggerPuppet(userName, password, host);

        for (String s : executionLog) {
            System.out.println(s);
        }
    }
}
