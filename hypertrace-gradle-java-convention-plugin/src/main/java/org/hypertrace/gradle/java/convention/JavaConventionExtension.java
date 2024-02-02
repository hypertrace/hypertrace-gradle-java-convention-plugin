package org.hypertrace.gradle.java.convention;

import javax.inject.Inject;
import org.gradle.api.JavaVersion;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.Property;
import org.gradle.jvm.toolchain.JavaLanguageVersion;

public class JavaConventionExtension {
  static final String EXTENSION_NAME = "javaConvention";
  // TODO: resolve if need two different for source & target
  public final Property<JavaVersion> compatibilityVersion;
  public final Property<JavaLanguageVersion> toolchainVersion;

  @Inject
  public JavaConventionExtension(ObjectFactory factory) {
    compatibilityVersion = factory.property(JavaVersion.class).convention(JavaVersion.VERSION_21);
    toolchainVersion =
        factory.property(JavaLanguageVersion.class).convention(JavaLanguageVersion.of(21));
  }
}
