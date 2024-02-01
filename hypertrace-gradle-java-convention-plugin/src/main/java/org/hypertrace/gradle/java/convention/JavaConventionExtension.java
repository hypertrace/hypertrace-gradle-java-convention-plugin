package org.hypertrace.gradle.java.convention;

import javax.inject.Inject;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.Property;

public class JavaConventionExtension {
  static final String EXTENSION_NAME = "javaConvention";
  public final Property<LanguageVersion> languageVersion;

  @Inject
  public JavaConventionExtension(ObjectFactory factory) {
    languageVersion = factory.property(LanguageVersion.class).convention(LanguageVersion.JAVA_21);
  }
}
