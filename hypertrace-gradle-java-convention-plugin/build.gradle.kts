plugins {
  `java-gradle-plugin`
  id("org.hypertrace.publish-plugin")
}

gradlePlugin {
  plugins {
    create("hypertraceJavaConventionPlugin") {
      id = "org.hypertrace.java-convention"
      implementationClass = "org.hypertrace.gradle.java.convention.JavaConventionPlugin"
    }
  }
}

java {
  targetCompatibility = JavaVersion.VERSION_11
  sourceCompatibility = JavaVersion.VERSION_11
}
