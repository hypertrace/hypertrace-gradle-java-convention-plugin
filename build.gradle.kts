import org.hypertrace.gradle.publishing.HypertracePublishExtension
import org.hypertrace.gradle.publishing.License

plugins {
  alias(commonLibs.plugins.hypertrace.publish)
  alias(commonLibs.plugins.hypertrace.ciutils)
  alias(commonLibs.plugins.hypertrace.codestyle)
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
