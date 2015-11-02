package se.mtm.gradle.extensions;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PluginDefaultTest {
    @Test
    public void set_utv_hosts() {
        String[] expected = {"a", "b"};

        GradlePuppetPluginDefaults gradlePuppetPluginDefaults = new GradlePuppetPluginDefaults();

        gradlePuppetPluginDefaults.setUtvHosts(new String[]{"a", "b"});

        String[] actual = gradlePuppetPluginDefaults.getUtvHosts();

        assertThat(actual, is(expected));
    }

    @Test
    public void set_test_hosts() {
        String[] expected = {"c", "d"};

        GradlePuppetPluginDefaults gradlePuppetPluginDefaults = new GradlePuppetPluginDefaults();

        gradlePuppetPluginDefaults.setTestHosts(new String[]{"c", "d"});

        String[] actual = gradlePuppetPluginDefaults.getTestHosts();

        assertThat(actual, is(expected));
    }

    @Test
    public void set_prod_hosts() {
        String[] expected = {"e", "f"};

        GradlePuppetPluginDefaults gradlePuppetPluginDefaults = new GradlePuppetPluginDefaults();

        gradlePuppetPluginDefaults.setProdHosts(new String[]{"e", "f"});

        String[] actual = gradlePuppetPluginDefaults.getProdHosts();

        assertThat(actual, is(expected));
    }

}
