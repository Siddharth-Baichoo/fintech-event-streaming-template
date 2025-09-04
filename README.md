
# fintech-event-streaming-template

Minimal multi-module Spring Boot template for an event-driven fintech stack.

## Modules

- **api**  
	REST API using Spring Boot, JPA (PostgreSQL), Kafka consumer, and Avro deserialization.  
	Handles payment endpoints and database persistence.

- **streaming**  
	Spring Boot app for Kafka Streams.  
	Includes Kafka Streams API, Avro SerDe, and integration with the shared domain.

- **ingestion**  
	Simple producer that emits payment events to Kafka.  
	Uses Avro serialization and Spring Boot.

- **common**  
	Shared domain models and repository logic.  
	Includes Avro schema/codegen, JPA annotations, and Lombok for boilerplate reduction.

## Technologies

- Spring Boot
- Kafka & Kafka Streams
- Avro (codegen, SerDe)
- PostgreSQL (via JPA)
- Lombok
- Gradle (Kotlin DSL)
- Docker Compose (for infra)
- Makefile (for common tasks)

## Quickstart

```bash
# 1) Start infrastructure (Kafka + Postgres)
make up

# 2) Run API (auto-creates tables)
make run-api

# 3) (Optional) Produce sample events
make run-ingestion

# 4) Try endpoints
curl http://localhost:8080/api/health
curl http://localhost:8080/api/payments
```

## Build

```bash
make build
```

## Avro Codegen

The `common` module uses Avro schemas (`src/main/avro/*.avsc`) and Gradle Avro plugin for code generation.  
Generated sources are available in `build/generated-main-avro-java`.

## Next Steps

- Add Kafka serializers (JSON/Avro)
- Implement real Streams topology in `streaming`
- Add Flyway migrations
- Dockerfiles per module
- GitHub Actions workflows
