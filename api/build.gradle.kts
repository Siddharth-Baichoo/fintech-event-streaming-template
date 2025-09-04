plugins { id("org.springframework.boot") }

dependencies {
    implementation(project(":common"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // Kafka consumer
    implementation("org.springframework.kafka:spring-kafka")

    // Confluent Avro deserializer
    implementation("io.confluent:kafka-avro-serializer:7.6.1")

    // Postgres driver
    runtimeOnly("org.postgresql:postgresql")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}
