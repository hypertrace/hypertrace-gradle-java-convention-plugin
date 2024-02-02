package org.hypertrace.gradle.java.convention;

import java.util.List;
import org.gradle.api.JavaVersion;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.JavaPluginExtension;
import org.gradle.api.tasks.testing.Test;
import org.gradle.jvm.toolchain.JavaToolchainService;
import org.gradle.jvm.toolchain.JavaToolchainSpec;

public class JavaConventionPlugin implements Plugin<Project> {

  public void apply(Project target) {
    // plugin meant to be applied at root level, it configures for all nested subprojects
    target
        .getSubprojects()
        .forEach(
            subproject -> {
              subproject
                  .getPluginManager()
                  .withPlugin(
                      "java",
                      unused -> {
                        createConvention(subproject);
                        configureCompatibility(subproject);
                        configureToolchain(subproject);
                        modifyTestJvmArgs(subproject);
                      });
              // recursive call
              apply(subproject);
            });
  }

  private void createConvention(Project target) {
    target
        .getExtensions()
        .create(JavaConventionExtension.EXTENSION_NAME, JavaConventionExtension.class);
  }

  private void configureCompatibility(Project target) {
    // TODO: resolve about usage of `.get()`
    JavaVersion javaVersion = javaConventionExtension(target).compatibilityVersion.get();
    javaPluginExtension(target).setSourceCompatibility(javaVersion);
    javaPluginExtension(target).setTargetCompatibility(javaVersion);
  }

  private void configureToolchain(Project target) {
    JavaToolchainSpec spec = javaPluginExtension(target).getToolchain();
    spec.getLanguageVersion().set(javaConventionExtension(target).toolchainVersion);
    javaToolchainService(target).compilerFor(spec);
    javaToolchainService(target).launcherFor(spec);
    javaToolchainService(target).javadocToolFor(spec);
  }

  private void modifyTestJvmArgs(Project target) {
    // required for junit environment variables for jdk 17+
    // https://junit-pioneer.org/docs/environment-variables/#warnings-for-reflective-access
    target
        .getTasks()
        .withType(Test.class)
        .configureEach(
            task -> {
              task.jvmArgs(
                  List.of(
                      "--add-opens",
                      "java.base/java.lang=ALL-UNNAMED",
                      "--add-opens",
                      "java.base/java.util=ALL-UNNAMED"));
            });
  }

  private JavaConventionExtension javaConventionExtension(Project target) {
    return target.getExtensions().getByType(JavaConventionExtension.class);
  }

  private JavaPluginExtension javaPluginExtension(Project target) {
    return target.getExtensions().getByType(JavaPluginExtension.class);
  }

  private JavaToolchainService javaToolchainService(Project target) {
    return target.getExtensions().getByType(JavaToolchainService.class);
  }
}
