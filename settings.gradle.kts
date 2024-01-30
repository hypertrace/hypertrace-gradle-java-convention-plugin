import org.hypertrace.gradle.dependency.DependencyPluginSettingExtension

pluginManagement {
  repositories {
    gradlePluginPortal()
    maven {
      url = uri("https://hypertrace.jfrog.io/artifactory/maven")
    }
  }
}

plugins {
  id("org.hypertrace.version-settings") version "0.2.1"
  id("org.hypertrace.dependency-settings") version "0.1.2"
}


configure<DependencyPluginSettingExtension> {
  catalogVersion.set("0.3.11")
}

rootProject.name = "hypertrace-gradle-java-convention-plugin"

include(":hypertrace-gradle-java-convention-plugin")
