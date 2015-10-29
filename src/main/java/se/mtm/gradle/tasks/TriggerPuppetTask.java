package se.mtm.gradle.tasks;

import org.gradle.api.DefaultTask;
import org.gradle.api.logging.Logger;
import org.gradle.api.tasks.TaskAction;
import se.mtm.gradle.extensions.PluginDefaults;

import java.io.IOException;
import java.util.List;

public class TriggerPuppetTask extends DefaultTask {
    @TaskAction
    public void triggerPuppet() throws IOException {
        Logger logger = getLogger();
        PluginDefaults extension = getProject().getExtensions().findByType(PluginDefaults.class);

        String user = System.getProperty("user");
        String password = System.getProperty("password");
        String host = System.getProperty("host");
        if (host == null) {
            host = extension.getHost();
        }

        logger.lifecycle("Trigger puppet on " + host);

        List<String> executionLog = SshExecutor.triggerPuppet(user, password, host);

        for (String line : executionLog) {
            logger.lifecycle(line);
        }
    }

}
