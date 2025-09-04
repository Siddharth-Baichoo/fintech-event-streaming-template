plugins {
    id("org.springframework.boot")
}

dependencies {
    implementation(project(":common"))

    // Spring Boot + Kafka
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.kafka:spring-kafka")

    // Kafka Streams API (version managed by Boot BOM)
    implementation("org.apache.kafka:kafka-streams")

    // ✅ Confluent Streams Avro SerDe (provides SpecificAvroSerde)
    implementation("io.confluent:kafka-streams-avro-serde:7.6.1")

    // Useful if you also use KafkaTemplate/Listener with Avro
    implementation("io.confluent:kafka-avro-serializer:7.6.1")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
