# Employee Management API (Quarkus)

This project is a RESTful API for managing employee information, built using the Quarkus framework.

## Features

- **Full CRUD operations** for Employees.
- **Jakarta REST (JAX-RS)** for RESTful endpoints.
- **Hibernate ORM with Panache** for simplified data persistence.
- **PostgreSQL** as the production-ready database.
- **Lombok** for reducing boilerplate code.
- **Spotless** with Google Java Format for consistent code style.
- **JaCoCo** for test coverage reporting.
- **Quarkus Dev Services** support for local development.

## Prerequisites

- **Java 17** (Managed via [mise](https://mise.jdx.sh/))
- **Maven 3.9+**
- **Docker** (for PostgreSQL)

## Setup and Installation

### 1. Database Configuration

The application is configured to connect to a PostgreSQL instance on `localhost:5433`.

```yaml
# src/main/resources/application.yml
quarkus:
  datasource:
    db-kind: postgresql
    username: admin
    password: admin
    jdbc:
      url: jdbc:postgresql://localhost:5433/admin
```

Ensure you have a PostgreSQL container running or use Quarkus Dev Services by setting `quarkus.devservices.enabled: true` in `application.yml`.

### 2. Environment Setup (Optional - with mise)

If you have `mise` installed, the tools will be automatically configured:

```shell
mise install
```

## Running the Application

### Development Mode

Run the application in dev mode with live coding enabled:

```shell
./mvnw quarkus:dev
```

### Packaging and Running

Package the application as a runnable JAR:

```shell
./mvnw package
```

Run the packaged application:

```shell
java -jar target/quarkus-app/quarkus-run.jar
```

## Testing and Quality

### Running Tests

Execute unit and integration tests:

```shell
./mvnw verify -DskipITs=false
```

### Code Coverage

After running the tests, the JaCoCo report is generated at:
`target/site/jacoco/index.html`

### Code Formatting

Apply Google Java Format using Spotless:

```shell
./mvnw spotless:apply
```

## API Endpoints

The base path for employee operations is `/employee`.

- `GET /employee/` - List all employees.
- `POST /employee/create` - Create a new employee.
- `PUT /employee/update/{id}` - Update an existing employee.
- `DELETE /employee/delete/{id}` - Delete an employee.

## Technologies Used

- [Quarkus](https://quarkus.io/)
- [Jakarta REST (JAX-RS)](https://jakarta.ee/specifications/restful-ws/)
- [Hibernate Panache](https://quarkus.io/guides/hibernate-orm-panache)
- [PostgreSQL](https://www.postgresql.org/)
- [Lombok](https://projectlombok.org/)
- [Spotless](https://github.com/diffplug/spotless)
- [JaCoCo](https://www.eclemmaia.org/jacoco/)
