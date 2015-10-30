# A Gradle plugin for triggering Puppet from Gradle

## Purposes

We want to be able to trigger Puppet from Gradle.


## Usage

How are you expected to use the plugin?

### Tasks

This task are available

* deployUtv - trigger puppet from Gradle and deploy on utv
* deployTest - trigger puppet from Gradle and deploy on test
* deployProd - trigger puppet from Gradle and deploy on prod

### Configuration

Get access to the plugin:

```Gradle
apply plugin: 'se.mtm.gradle-puppet'

buildscript {
    repositories {
        mavenLocal()
    }
    dependencies {
        classpath 'se.mtm.gradle:gradle-puppet-plugin:1.0.5'
    }
}
```

The version number, `1.0.5`, is wrong. Update it with the latest release.

Configure it:

```Gradle
gradlePuppet {
    utvHosts = ['statusutv1.mtm.se']
    testHosts = ['statusutv1.mtm.se']
    prodHosts = ['statusutv1.mtm.se']
}
```

The properties that can be set are

* String[] utvHosts - the utv hosts
* String[] testHosts - the tests hosts
* String[] prodHosts - the prod hosts

The truth and default values are defined in [src/main/java/se/mtm/gradle/extensions/PluginDefaults](https://github.com/mtmse/gradle-puppet-plugin/blob/master/src/main/java/se/mtm/gradle/extensions/PluginDefaults.java)

### Environment variable

The plugin need access to a user credentials for communicating with the target hosts. The password is already set in Jenkins, this solution piggy back on that password.
Set

* ARTIFACTORY_PASSWORD

to the password for the you use for running Puppet. If you are using Jenkins, then set them
as environment variables in Jenkins. You find the settings by following these menus items:

`Jenkins | Manage Jenkins | Configure System | Environment variables`


### User and password

It is possible to set the user and password using regular java properties. Execute Gradle with

`./gradlew deployUtv -Duser=theUserYouWantToExecuteAs -Dpassword=thePAsswordYouWantToUse`

## Developing

Build

`./gradlew clean build publishToMavenLocal --daemon`

## Decision log

* If no user is set, tpbadmin is assumed
* Password is read from the environment for minimizing job configuration

## Resources

[Gradle, Writing Custom Plugins](https://gradle.org/docs/current/userguide/custom_plugins.html)
