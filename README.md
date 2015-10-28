# A Gradle plugin for triggering Puppet from Gradle

## Purposes

We want to be able to trigger Puppet from Gradle.


## Usage

How are you expected to use the plugin?

### Tasks

This task are available

* triggerPuppet - trigger puppet from Gradle

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

The version number, `1.0.5`, is obsolete. Update it with the latest release.

Configure it:

```Gradle
gradlePuppet {
    host = "statusutv1.mtm.se"
}
```

The properties that can be set are

* String user - the user you should execute puppet as. If it isn't set, it will be read from the environment.
* String password - the password for the user. If it isn't set, it will be read from the environment.

The truth and default values are defined in [src/main/java/se/mtm/gradle/extensions/PluginDefaults](https://github.com/mtmse/gradle-puppet-plugin/blob/master/src/main/java/se/mtm/gradle/extensions/PluginDefaults.java)

### Environment variables

The plugin need access to a user credentials for communicating with Artifactory. These should be set as environment variables.
Set

* PUPPET_USER
* PUPPET_USER_PASSWORD

to the user and password you use for running Puppet. If you are using Jenkins, then set them
as environment variables in Jenkins. You find the settings by following these menus items:

`Jenkins | Manage Jenkins | Configure System | Environment variables`

## Developing

Build

`./gradlew clean build publishToMavenLocal --daemon`

## Decision log

No important decision has ben taken yet.

## Resources

[Artifactory REST Api](http://www.jfrog.com/confluence/display/RTF/Artifactory+REST+API)

[JFrog project examples on GitHub](https://github.com/JFrogDev/project-examples)

[Gradle, Writing Custom Plugins](https://gradle.org/docs/current/userguide/custom_plugins.html)
