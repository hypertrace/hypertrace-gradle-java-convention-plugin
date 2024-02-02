package org.hypertrace.gradle.java.convention;

import java.util.List;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.JavaPluginExtension;
import org.gradle.api.tasks.compile.JavaCompile;
import org.gradle.api.tasks.testing.Test;
import org.gradle.jvm.toolchain.JavaLanguageVersion;

public class JavaConventionPlugin implements Plugin<Project> {

  public void apply(Project target) {
    target
        .getAllprojects()
        .forEach(
            project ->
                project
                    .getPluginManager()
                    .withPlugin(
                        "java",
                        unused -> {
                          createConvention(project);
                          configureCompilerRelease(project);
                          configureToolchain(project);
                          modifyTestJvmArgs(project);
                        }));
  }

  private void createConvention(Project target) {
    target
        .getExtensions()
        .create(JavaConventionExtension.EXTENSION_NAME, JavaConventionExtension.class);
  }

  private void configureCompilerRelease(Project target) {
    target
        .getTasks()
        .withType(JavaCompile.class)
        .configureEach(
            task ->
                task.getOptions()
                    .getRelease()
                    .set(
                        javaConventionExtension(target)
                            .compilerRelease
                            .map(JavaLanguageVersion::asInt)));
  }

  private void configureToolchain(Project target) {
    javaPluginExtension(target)
        .getToolchain()
        .getLanguageVersion()
        .set(javaConventionExtension(target).toolchainVersion);
  }

  private void modifyTestJvmArgs(Project target) {
    // required for junit environment variables for jdk 17+
    // https://junit-pioneer.org/docs/environment-variables/#warnings-for-reflective-access
    target
        .getTasks()
        .withType(Test.class)
        .configureEach(
            task ->
                task.jvmArgs(
                    List.of(
                        "--add-opens",
                        "java.base/java.lang=ALL-UNNAMED",
                        "--add-opens",
                        "java.base/java.util=ALL-UNNAMED")));
  }

  private JavaConventionExtension javaConventionExtension(Project target) {
    return target.getExtensions().getByType(JavaConventionExtension.class);
  }

  private JavaPluginExtension javaPluginExtension(Project target) {
    return target.getExtensions().getByType(JavaPluginExtension.class);
  }
}
