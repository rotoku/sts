# Copilot Instructions

## Project: Java 21 with Spring Boot 3 and Maven

### 1. Core Technologies:
- **Java Version:** Ensure all Java code is compatible with Java 21. Leverage Java 21 features where appropriate (e.g., virtual threads, record patterns, pattern matching for switch).
- **Spring Boot Version:** Use Spring Boot 3.x.x. Adhere to Spring Boot conventions and best practices.
- **Build Tool:** Use Maven for dependency management and build processes. Ensure `pom.xml` is kept clean and dependencies are up-to-date.
- **Spring Framework:** Utilize core Spring Framework features like Dependency Injection, AOP, and Spring MVC/WebFlux as appropriate.

### 2. Architecture: Clean Architecture
- **Layering:** Strictly adhere to Clean Architecture principles. Code should be organized into distinct layers (e.g., Domain, Application, Infrastructure, Presentation/Adapters).
    - **Domain Layer:** Contains enterprise-wide business rules and entities. It should be independent of other layers. No Spring Boot annotations here unless absolutely necessary and justified.
    - **Application Layer:** Contains application-specific business rules (use cases). Orchestrates data flow between the Domain and Infrastructure layers.
    - **Infrastructure Layer:** Contains external concerns like database access (Spring Data JPA), external API clients, message queues, etc. Implements interfaces defined in the Application or Domain layers.
    - **Presentation/Adapters Layer:** Contains UI (e.g., REST controllers using Spring MVC/WebFlux), CLI, or other delivery mechanisms.
- **Dependency Rule:** Dependencies must only point inwards. The Domain layer should have no dependencies on other layers. Application layer depends on Domain. Infrastructure and Presentation depend on Application and Domain (through interfaces).
- **Use Cases:** Implement application-specific logic as use cases or interactors in the Application layer.
- **Entities:** Domain entities should be plain Java objects (POJOs) representing core business concepts.
- **Interfaces/Ports and Adapters:** Define interfaces (ports) in the inner layers (Domain, Application) and implement them (adapters) in the outer layers (Infrastructure, Presentation).

### 3. Coding Standards & Best Practices:
- **Java Conventions:** Follow standard Java naming conventions (PascalCase for classes, camelCase for methods and variables, UPPER_SNAKE_CASE for constants).
- **Readability:** Write clean, readable, and maintainable code. Use meaningful names for variables, methods, and classes.
- **SOLID Principles:** Apply SOLID principles (Single Responsibility, Open/Closed, Liskov Substitution, Interface Segregation, Dependency Inversion).
- **Immutability:** Prefer immutability for DTOs and value objects where possible. Use Java records for simple data carriers.
- **Error Handling:** Use appropriate exception handling. Prefer custom, specific exceptions over generic ones. Clearly document expected exceptions.
- **Logging:** Use a consistent logging framework (e.g., SLF4J with Logback/Log4j2). Log meaningful messages at appropriate levels.
- **Comments:** Write Javadoc for all public classes, methods, and non-obvious code sections. Explain *why* something is done, not just *what* it does.
- **Code Reviews:** When suggesting changes or new code, act as if you are participating in a code review. Point out potential issues and suggest improvements based on these instructions.

### 4. Testing:
- **Unit Tests:**
    - **Mandatory:** Every new class and public method *must* have corresponding unit tests.
    - **Framework:** Use JUnit 5 and Mockito (or a similar mocking framework).
    - **Coverage:** Aim for high test coverage, especially for business logic in the Domain and Application layers.
    - **Assertions:** Use clear and specific assertions (e.g., AssertJ).
    - **Test Naming:** Test methods should clearly describe what they are testing (e.g., `givenX_whenY_thenZ`).
    - **Isolation:** Unit tests should be isolated and not depend on external systems (databases, network calls). Mock dependencies.
- **Integration Tests:** Suggest integration tests for interactions between layers (e.g., Application layer with Infrastructure layer) or with external services. Use Spring Boot's testing support (`@SpringBootTest`).
- **Test Data:** Use realistic and varied test data.

### 5. Copilot Interaction:
- **Code Generation:** When generating code, adhere to all the above principles.
- **Refactoring:** When asked to refactor, focus on improving code quality, readability, and adherence to clean architecture and SOLID principles.
- **Explanations:** When explaining code, relate it back to the architectural layers and design principles.
- **Dependency Management:** When suggesting new dependencies, explain the reason and ensure they are added correctly to `pom.xml`.
- **Spring Boot Features:** When suggesting solutions, leverage Spring Boot auto-configuration and starters where appropriate, but always consider the impact on clean architecture principles (e.g., avoid leaking Spring annotations into the domain layer).
- **Proactive Suggestions:** If you identify code that violates these instructions (e.g., missing tests, architectural violations), proactively suggest improvements.

