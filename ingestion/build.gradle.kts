plugins { id("org.springframework.boot") }

dependencies {
    implementation(project(":common"))

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.kafka:spring-kafka")

    // Confluent Avro serializer (needs the Confluent repo at root)
    implementation("io.confluent:kafka-avro-serializer:7.6.1")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
