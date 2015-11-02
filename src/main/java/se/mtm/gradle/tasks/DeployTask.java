package se.mtm.gradle.tasks;

import org.gradle.api.DefaultTask;
import org.gradle.api.logging.Logger;
import org.gradle.api.tasks.TaskAction;
import se.mtm.gradle.extensions.GradlePuppetPluginDefaults;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public abstract class DeployTask extends DefaultTask {
    @TaskAction
    public void deploy() throws IOException {
        Logger logger = getLogger();
        GradlePuppetPluginDefaults extension = getProject().getExtensions().findByType(GradlePuppetPluginDefaults.class);

        String user = System.getProperty("user");
        String password = System.getProperty("password");
        String[] hosts = getHosts(extension);

        List<String> executionLog = Collections.emptyList();
        for (String host : hosts) {
            logger.lifecycle("Trigger puppet on " + host);
            executionLog = SshExecutor.triggerPuppet(user, password, host);
        }

        for (String line : executionLog) {
            logger.lifecycle(line);
        }
    }

    abstract String[] getHosts(GradlePuppetPluginDefaults extension);
}
