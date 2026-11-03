# User Management API

A small Java 21 / Spring Boot 4.1.2 application used as the single running example for a four-part Medium series about testing Spring Boot applications.

## Features

1. Local user management backed by Spring Data JPA and an H2 in-memory database.
2. External user lookup through Spring `RestClient` and JSONPlaceholder.

## Technology

- Java 21
- Spring Boot 4.1.2
- Spring Web
- Spring Data JPA
- Spring Validation
- Spring `RestClient`
- H2
- JUnit 5 and Spring Boot Test (test dependency)

## Run the application

```bash
mvn spring-boot:run
```

The application starts on `http://localhost:8080`.

## Local database API

Create a user:

```bash
curl -i -X POST http://localhost:8080/api/users \
  -H 'Content-Type: application/json' \
  -d '{"name":"Alex Morgan","email":"alex@example.com"}'
```

Get one user (replace `1` with the returned ID):

```bash
curl -i http://localhost:8080/api/users/1
```

List users:

```bash
curl -i http://localhost:8080/api/users
```

Update a user:

```bash
curl -i -X PUT http://localhost:8080/api/users/1 \
  -H 'Content-Type: application/json' \
  -d '{"name":"Alex Morgan","email":"alex.morgan@example.com"}'
```

Delete a user:

```bash
curl -i -X DELETE http://localhost:8080/api/users/1
```

## External API

Fetch user 1 from JSONPlaceholder:

```bash
curl -i http://localhost:8080/api/external-users/1
```

This endpoint requires an internet connection when the request is made. Automated tests should not rely on JSONPlaceholder being available; Part 4 will show how to test the HTTP client without calling the live service.

## H2 console

Open `http://localhost:8080/h2-console` and use:

- JDBC URL: `jdbc:h2:mem:usersdb`
- User Name: `sa`
- Password: *(leave blank)*

The database is in-memory. Its contents are lost when the application stops.

## Testing-series roadmap

- Part 1: JUnit 5 fundamentals and service unit tests with/without Mockito.
- Part 2: Controller tests with standalone MockMvc and `@WebMvcTest`.
- Part 3: Repository tests with `@DataJpaTest` and H2.
- Part 4: External HTTP client tests and full-context integration tests.

## Notes

This is a teaching application, not a production-ready user management system. For example, it does not implement authentication, authorisation, duplicate-email handling, pagination, or production database migrations. Those concerns are intentionally outside the initial testing example.
