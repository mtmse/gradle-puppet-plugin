package se.mtm.gradle.extensions;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PluginDefaultTest {
    @Test
    public void set_utv_hosts() {
        String[] expected = {"a", "b"};

        PluginDefaults pluginDefaults = new PluginDefaults();

        pluginDefaults.setUtvHosts(new String[]{"a", "b"});

        String[] actual = pluginDefaults.getUtvHosts();

        assertThat(actual, is(expected));
    }

    @Test
    public void set_test_hosts() {
        String[] expected = {"c", "d"};

        PluginDefaults pluginDefaults = new PluginDefaults();

        pluginDefaults.setTestHosts(new String[]{"c", "d"});

        String[] actual = pluginDefaults.getTestHosts();

        assertThat(actual, is(expected));
    }

    @Test
    public void set_prod_hosts() {
        String[] expected = {"e", "f"};

        PluginDefaults pluginDefaults = new PluginDefaults();

        pluginDefaults.setProdHosts(new String[]{"e", "f"});

        String[] actual = pluginDefaults.getProdHosts();

        assertThat(actual, is(expected));
    }

}
