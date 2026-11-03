# User Management API — Spring Boot Testing Guide

A beginner-friendly sample application used throughout the five-part series **A Practical Beginner's Guide to Testing Spring Boot Applications**.

The project uses one Spring Boot application to demonstrate testing at different levels, from isolated unit tests with JUnit 5 and Mockito to repository and application integration tests, code coverage, and Test-Driven Development (TDD).

## 📚 Series Roadmap

| Part | Article | What You'll Learn |
|---|---|---|
| **Part 1** | Testing Fundamentals and Unit Testing with JUnit 5 and Mockito | Testing fundamentals, JUnit 5, Mockito, plain unit tests and service unit tests |
| **Part 2** | Testing REST APIs and Controllers with MockMvc | Controller tests, MockMvc, `@WebMvcTest`, HTTP responses, request validation and exception handling |
| **Part 3** | Testing Repositories and Databases with Spring Boot and H2 | Repository tests, `@DataJpaTest`, CRUD operations, custom queries and H2 |
| **Part 4** | Integration Testing in Spring Boot: External APIs, Code Coverage and the Complete Application | External HTTP client tests, `@SpringBootTest`, application integration, JaCoCo and code coverage |
| **Part 5** | Test-Driven Development (TDD) with Spring Boot: Build Features by Writing Tests First | Red-Green-Refactor cycle and implementing a feature by writing tests first |

*Links to the articles will be added here as they are published.*

---

## 🚀 Application Overview

The application has two main features.

### 1. Local User Management

A REST API for creating, retrieving, updating and deleting users. User data is stored in an H2 in-memory relational database using Spring Data JPA.

**Application flow:**

```text
       HTTP Request
            |
            v
      UserController
            |
            v
       UserService
            |
            v
     UserRepository
            |
            v
       H2 Database
```

### 2. External User Lookup

A separate endpoint retrieves user information from the public JSONPlaceholder API using Spring's `RestClient`.

**Application flow:**

```text
       HTTP Request
            |
            v
 ExternalUserController
            |
            v
   ExternalUserService
            |
            v
   ExternalUserClient
            |
            v
   JSONPlaceholder API
```

## 🛠️ Technology Stack

- **Java:** 21
- **Spring Boot:** 4.1.2
- **Spring Web**
- **Spring Data JPA**
- **Spring Validation**
- **Spring RestClient**
- **H2 Database**
- **JUnit 5**
- **Mockito**
- **Maven**

---

## 📋 Prerequisites

Install the following before running the application:

- JDK 21
- Maven 3.9 or later
- An IDE such as IntelliJ IDEA (optional)

Verify your installations:

```bash
java -version
mvn -version
```

## ⚙️ Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/<your-github-username>/spring-boot-testing-guide.git
```

### 2. Navigate to the project directory

```bash
cd spring-boot-testing-guide
```

### 3. Build the project and run tests

```bash
mvn clean test
```

### 4. Start the application

```bash
mvn spring-boot:run
```

The application will be available at:

```text
http://localhost:8080
```

---

## 🌐 REST API Endpoints

### Local User Management

| HTTP Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/users` | Create a user |
| `GET` | `/api/users` | Retrieve all users |
| `GET` | `/api/users/{id}` | Retrieve a user by ID |
| `PUT` | `/api/users/{id}` | Update a user |
| `DELETE` | `/api/users/{id}` | Delete a user |

### Example API Requests

**Create a user**

```bash
curl -i -X POST http://localhost:8080/api/users \
  -H 'Content-Type: application/json' \
  -d '{"name":"Alex Morgan","email":"alex@example.com"}'
```

**Retrieve a user**

Replace `1` with the ID returned by the create request.

```bash
curl -i http://localhost:8080/api/users/1
```

**Retrieve all users**

```bash
curl -i http://localhost:8080/api/users
```

**Update a user**

```bash
curl -i -X PUT http://localhost:8080/api/users/1 \
  -H 'Content-Type: application/json' \
  -d '{"name":"Alex Smith","email":"alex.smith@example.com"}'
```

**Delete a user**

```bash
curl -i -X DELETE http://localhost:8080/api/users/1
```

### External User Lookup

Retrieve user information from JSONPlaceholder:

```bash
curl -i http://localhost:8080/api/external-users/1
```

This endpoint requires an internet connection when called.

Tests should not depend on the live public API. The series demonstrates how to test external HTTP interactions in a controlled way.

---

## ✅ Input Validation

`UserRequest` uses Jakarta Bean Validation annotations.

Examples include:

- `@NotBlank` — Ensures the user's name and email are not blank.
- `@Email` — Validates the email address format.

The controller triggers validation for incoming requests.

Controller-level validation behaviour is explored in Part 2.

---

## 🗄️ H2 Database Console

When the application is running, open:

```text
http://localhost:8080/h2-console
```

Use the following connection details:

| Field | Value |
|---|---|
| JDBC URL | `jdbc:h2:mem:usersdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE` |
| User Name | `sa` |
| Password | Leave blank |

The database is in memory. Its data is not intended to persist after the application shuts down.

**Note:** The H2 console is intended for local development and learning, not production use.

---

## 📁 Project Structure

```text
user-management-api/
│
├── src/
│   ├── main/
│   │   ├── java/com/example/users/testpractice/
│   │   │   ├── client/
│   │   │   ├── controller/
│   │   │   ├── dto/
│   │   │   ├── entity/
│   │   │   ├── exception/
│   │   │   ├── repository/
│   │   │   ├── service/
│   │   │   └── UserManagementApplication.java
│   │   │
│   │   └── resources/
│   │       └── application.properties
│   │
│   └── test/
│       └── java/com/example/users/testpractice/
│
├── pom.xml
└── README.md
```

Tests will be added to the corresponding packages under `src/test/java` as the series progresses.

---

## 🧪 Running Tests

### Run all tests

```bash
mvn clean test
```

### Run a specific test class

```bash
mvn -Dtest=UserServiceTest test
```

### View test reports

Maven Surefire generates detailed test reports under:

```text
target/surefire-reports/
```

---

## 📊 What You'll Learn Through This Project

By following the five-part series, you'll learn how to:

- Write unit tests using JUnit 5.
- Mock dependencies using Mockito.
- Test service and business logic independently.
- Test REST controllers using MockMvc.
- Test JPA repositories using `@DataJpaTest`.
- Work with an H2 in-memory database during tests.
- Test external REST API integrations.
- Write full application integration tests using `@SpringBootTest`.
- Measure code coverage using JaCoCo.
- Apply Test-Driven Development using the Red-Green-Refactor cycle.

---

## 🎯 Project Scope

This is a learning project designed to demonstrate testing concepts in a Spring Boot application. It is not intended to be a production-ready user management system.

Features such as authentication, authorisation, pagination, duplicate-email handling, production database configuration and database migrations are outside the current scope.

The application and test examples will evolve as each part of the series is published.

## 🤝 Contributions

This repository is open to developers who want to learn or practise Spring Boot testing.

Suggestions