import org.hypertrace.gradle.dependency.DependencyPluginSettingExtension

pluginManagement {
  repositories {
    gradlePluginPortal()
    maven("https://us-maven.pkg.dev/hypertrace-repos/maven")
  }
}

plugins {
  id("org.hypertrace.version-settings") version "0.3.0"
  id("org.hypertrace.dependency-settings") version "0.2.0"
}

configure<DependencyPluginSettingExtension> {
  catalogVersion.set("0.3.48")
}

rootProject.name = "hypertrace-gradle-java-convention-plugin"

include(":hypertrace-gradle-java-convention-plugin")
