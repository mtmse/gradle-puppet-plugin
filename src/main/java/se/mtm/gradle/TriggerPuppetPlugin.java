package se.mtm.gradle;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import se.mtm.gradle.extensions.PluginDefaults;
import se.mtm.gradle.tasks.TriggerPuppetTask;

import javax.inject.Inject;

class TriggerPuppetPlugin implements Plugin<Project> {
    @Inject
    public TriggerPuppetPlugin() {
    }

    @Override
    public void apply(Project project) {
        project.getExtensions().create("gradlePuppet", PluginDefaults.class);
        project.getTasks().create("triggerPuppet", TriggerPuppetTask.class);
    }
}
