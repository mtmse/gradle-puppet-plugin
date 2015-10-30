package se.mtm.gradle;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import se.mtm.gradle.extensions.PluginDefaults;
import se.mtm.gradle.tasks.DeployUtvTask;
import se.mtm.gradle.tasks.TriggerPuppetTask;

import javax.inject.Inject;

class TriggerPuppetPlugin implements Plugin<Project> {
    private static final String ARTIFACTORY_PASSWORD = "ARTIFACTORY_PASSWORD";

    @Inject
    public TriggerPuppetPlugin() {
    }

    @Override
    public void apply(Project project) {
        project.getExtensions().create("gradlePuppet", PluginDefaults.class);
        getUser();
        getPassword();
        project.getTasks().create("triggerPuppet", TriggerPuppetTask.class);
        project.getTasks().create("deployUtv", DeployUtvTask.class);
    }

    private void getUser() {
        String user = System.getProperty("user");
        if (user == null) {
            user = "tpbadmin";
        }
        System.setProperty("user", user);
    }

    private void getPassword() {
        String password = System.getProperty("password");
        if (password == null) {
            password = System.getenv(ARTIFACTORY_PASSWORD);
        }

        if (password != null) {
            System.setProperty("password", password);
        }
    }
}
