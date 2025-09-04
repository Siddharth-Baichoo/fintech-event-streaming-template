# fintech-event-streaming (template)

Minimal multi-module Spring Boot template for an event-driven fintech stack.

## Modules
- `api` — REST API + JPA (PostgreSQL)
- `streaming` — placeholder Spring Boot app for Kafka Streams
- `ingestion` — simple producer that emits a few payment events
- `common` — shared domain and repository

## Quickstart
```bash
# 1) Start infra (Kafka + Postgres)
make up

# 2) Run API (will auto-create tables)
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

> Notes: This is intentionally small. Next steps: add Kafka serializers (JSON/Avro), real Streams topology in `streaming`, Flyway migrations, Dockerfiles per module, and GitHub Actions workflows.
