
// Top-level build file where you can add configuration options common to all sub-projects/modules.


buildscript {
    dependencies {
        classpath("com.github.dcendents:android-maven-gradle-plugin:2.1")
    }

    dependencies {
        classpath("com.android.tools.build:gradle:8.2.0")
    }
    repositories {
        google()
    }

}

plugins {
    id("com.android.application") version "7.0.4" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("com.android.library") version "8.2.0" apply false
}

