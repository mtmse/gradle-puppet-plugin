package se.mtm.gradle.extensions;

import java.util.Arrays;

public class GradlePuppetPluginDefaults {
    private String[] utvHosts = {};
    private String[] testHosts = {};
    private String[] prodHosts = {};

    public void setUtvHosts(String[] hosts) {
        int length = hosts.length;
        this.utvHosts = Arrays.copyOf(hosts, length);
    }

    public String[] getUtvHosts() {
        return utvHosts;
    }

    public void setTestHosts(String[] hosts) {
        int length = hosts.length;
        this.testHosts = Arrays.copyOf(hosts, length);
    }

    public String[] getTestHosts() {
        return testHosts;
    }

    public void setProdHosts(String[] hosts) {
        int length = hosts.length;
        this.prodHosts = Arrays.copyOf(hosts, length);
    }

    public String[] getProdHosts() {
        return prodHosts;
    }
}
