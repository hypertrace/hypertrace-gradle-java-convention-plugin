package org.hypertrace.gradle.java.convention;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.JavaPluginExtension;
import org.gradle.jvm.toolchain.JavaToolchainService;
import org.gradle.jvm.toolchain.JavaToolchainSpec;

public class JavaConventionPlugin implements Plugin<Project> {

  public void apply(Project target) {
    target
        .getPluginManager()
        .withPlugin(
            "java",
            unused -> {
              createConvention(target);
              configureToolchain(target);
            });
  }

  private void createConvention(Project target) {
    target
        .getExtensions()
        .create(JavaConventionExtension.EXTENSION_NAME, JavaConventionExtension.class);
  }

  private void configureToolchain(Project target) {
    JavaToolchainSpec spec = javaPluginExtension(target).getToolchain();
    spec.getLanguageVersion().set(javaConventionExtension(target).languageVersion);
    javaToolchainService(target).compilerFor(spec);
    javaToolchainService(target).launcherFor(spec);
    javaToolchainService(target).javadocToolFor(spec);
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
