
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version ("8.0.0-rc01") apply false
    id("com.android.library") version ("8.0.0-rc01") apply false
    id("org.jetbrains.kotlin.android") version ("1.7.20") apply false
}

buildscript {

    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.21")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.28-alpha")
    }
}


