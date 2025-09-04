plugins {
    id("org.springframework.boot") version "3.3.3" apply false
    id("io.spring.dependency-management") version "1.1.6" apply false
    // do NOT declare: id("java") apply false
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "io.spring.dependency-management")

    group = "com.siddharthbaichoo"
    version = "0.1.0"

    // Repos (Confluent is needed for Avro SerDes)
    repositories {
        mavenCentral()
        maven { url = uri("https://packages.confluent.io/maven/") }
    }

    // Java toolchain
    extensions.configure<org.gradle.api.plugins.JavaPluginExtension> {
        toolchain {
            languageVersion.set(org.gradle.jvm.toolchain.JavaLanguageVersion.of(21))
        }
    }

    // Test platform
    tasks.withType<org.gradle.api.tasks.testing.Test>().configureEach {
        useJUnitPlatform()
    }

    // Encoding (optional)
    tasks.withType<org.gradle.api.tasks.compile.JavaCompile>().configureEach {
        options.encoding = "UTF-8"
    }
}
