package org.hypertrace.gradle.java.convention;

import org.gradle.api.JavaVersion;
import org.gradle.jvm.toolchain.JavaLanguageVersion;

public enum LanguageVersion {
  JAVA_11(JavaVersion.VERSION_11, JavaLanguageVersion.of(11)),
  JAVA_21(JavaVersion.VERSION_21, JavaLanguageVersion.of(21));

  final JavaVersion javaVersion;
  final JavaLanguageVersion javaLanguageVersion;

  LanguageVersion(JavaVersion javaVersion, JavaLanguageVersion javaLanguageVersion) {
    this.javaVersion = javaVersion;
    this.javaLanguageVersion = javaLanguageVersion;
  }
}
