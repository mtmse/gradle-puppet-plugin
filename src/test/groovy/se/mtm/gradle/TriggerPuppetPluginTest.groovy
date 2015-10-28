package se.mtm.gradle

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Test

import se.mtm.gradle.tasks.TriggerPuppetTask

import static org.junit.Assert.assertTrue

class TriggerPuppetPluginTest {
    @Test
    public void should_find_tasks_on_plugin() {
        Project project = ProjectBuilder.builder().build()

        project.getPlugins().apply 'se.mtm.gradle-puppet'

        assertTrue(project.tasks.triggerPuppet instanceof TriggerPuppetTask)
    }
}
