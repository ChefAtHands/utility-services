# Logging Service

Spring Boot service responsible for collecting and persisting application logs and audit events used by ChefAtHands components.

What this code contains
- Spring Boot application sourcing configuration from `src/main/resources/application.properties`.
- Uses JDBC (configured in `application.properties`) to persist logs/audit records to a SQL Server database.
- Logback configuration is provided by `src/main/resources/logback-spring.xml` and relies on Spring Boot's default Logback setup.

Key files
- `src/main/java/.../LoggingServiceApplication.java` — application entry point.
- `src/main/java/.../controller` — HTTP controllers (if present) exposing endpoints for log ingestion or query.
- `src/main/java/.../model` — JPA entities used to store log/audit records.
- `src/main/java/.../repository` — Spring Data repositories for persistence.
- `src/main/resources/application.properties` — main runtime configuration (database, server port, JPA settings).
- `src/main/resources/logback-spring.xml` — Logback configuration (includes Spring Boot defaults).

Configuration highlights
- `spring.application.name` — service name used for service discovery and logs.
- `server.port` — HTTP port; default set in `application.properties` (modifiable via environment variable `SERVER_PORT`).
- `spring.datasource.*` — JDBC settings for SQL Server; credentials and URL are defined in `application.properties` for local runs. Prefer environment variables for production deployments.
- `spring.jpa.hibernate.ddl-auto` — controls schema management (set to `update` here; adjust for production).

Running locally
1. Build the service:

```powershell
cd utility-services/logging-service
mvn package
```

2. Run with Maven:

```powershell
mvn spring-boot:run
```

Or run the packaged JAR:

```powershell
java -jar target/logging-service-<version>.jar
```

Notes on database and security
- The service is configured to connect to a Microsoft SQL Server instance by default. Update `spring.datasource.url`, `spring.datasource.username` and `spring.datasource.password` to match your environment or provide them through environment variables.
- For production, do not commit plaintext credentials to `application.properties`; use a secrets manager or environment variables.

Logging behaviour
- Logback uses the Spring Boot base configuration (see `logback-spring.xml`). Customize this file to add appenders (file, console, remote) or to route logs to external aggregators (Logstash, Elasticsearch, etc.).

