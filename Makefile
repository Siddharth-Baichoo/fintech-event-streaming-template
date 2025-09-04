.PHONY: up down build run test

up:
	docker compose up -d

down:
	docker compose down -v

build:
	./gradlew clean build

run-api:
	./gradlew :api:bootRun

run-streaming:
	./gradlew :streaming:bootRun

run-ingestion:
	./gradlew :ingestion:bootRun

test:
	./gradlew test
