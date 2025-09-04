plugins {
    `java-library`
    id("com.github.davidmc24.gradle.plugin.avro") version "1.9.1"
}

dependencies {
    // Only annotations so @Entity compiles
    compileOnly("jakarta.persistence:jakarta.persistence-api:3.1.0")

    // Avro at compile/runtime (explicit version)
    implementation("org.apache.avro:avro:1.11.3")

    testImplementation("org.junit.jupiter:junit-jupiter:5.10.3")

    compileOnly("org.projectlombok:lombok:1.18.32")
    annotationProcessor("org.projectlombok:lombok:1.18.32")

}

tasks.test {
    useJUnitPlatform()
}

// Avro codegen (optional niceties)
avro {
    isCreateSetters.set(true)
    fieldVisibility.set("PRIVATE")
}

// Make generated sources visible to the compiler
sourceSets {
    named("main") {
        java.srcDir(layout.buildDirectory.dir("generated-main-avro-java"))
    }
}
