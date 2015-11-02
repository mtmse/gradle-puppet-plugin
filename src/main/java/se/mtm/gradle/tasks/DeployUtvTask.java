package se.mtm.gradle.tasks;

import se.mtm.gradle.extensions.GradlePuppetPluginDefaults;

public class DeployUtvTask extends DeployTask {
    @Override
    String[] getHosts(GradlePuppetPluginDefaults extension) {
        return extension.getUtvHosts();
    }
}
