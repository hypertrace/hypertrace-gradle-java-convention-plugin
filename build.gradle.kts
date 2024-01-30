import org.hypertrace.gradle.publishing.HypertracePublishExtension
import org.hypertrace.gradle.publishing.License

plugins {
  id("org.hypertrace.ci-utils-plugin") version "0.3.2"
  id("org.hypertrace.code-style-plugin") version "1.2.0"
  id("org.hypertrace.publish-plugin") version "1.0.6" apply false
  id("org.hypertrace.repository-plugin") version "0.4.2"
}

subprojects {
  group = "org.hypertrace.gradle.java.convention"
  apply(plugin = "org.hypertrace.code-style-plugin")
  pluginManager.withPlugin("org.hypertrace.publish-plugin") {
    configure<HypertracePublishExtension> {
      license.set(License.APACHE_2_0)
    }
  }
}
