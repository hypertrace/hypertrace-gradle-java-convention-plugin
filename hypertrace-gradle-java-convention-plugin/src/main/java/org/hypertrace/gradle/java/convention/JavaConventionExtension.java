package org.hypertrace.gradle.java.convention;

import javax.inject.Inject;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.Property;
import org.gradle.jvm.toolchain.JavaLanguageVersion;

public class JavaConventionExtension {
  static final String EXTENSION_NAME = "javaConvention";
  public final Property<JavaLanguageVersion> languageVersion;

  @Inject
  public JavaConventionExtension(ObjectFactory factory) {
    languageVersion =
        factory.property(JavaLanguageVersion.class).convention(JavaLanguageVersion.of(11));
  }
}
