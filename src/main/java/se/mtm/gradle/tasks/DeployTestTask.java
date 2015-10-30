package se.mtm.gradle.tasks;

import se.mtm.gradle.extensions.PluginDefaults;

public class DeployTestTask extends DeployTask {
    @Override
    String[] getHosts(PluginDefaults extension) {
        return extension.getTestHosts();
    }
}
