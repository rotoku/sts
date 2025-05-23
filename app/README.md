# STS - Security Token Service

This project implements a Security Token Service (STS) using Spring Boot 3 and Java 21. The STS is capable of generating access tokens based on HTTP POST requests with form URL encoded parameters: `grant_type`, `client_id`, and `client_secret`. 

## Project Structure

The project follows a clean architecture approach and is structured as follows:

- **src/main/java/com/example/sts**: Contains the main application code.
  - **StsApplication.java**: Entry point of the Spring Boot application.
  - **config**: Contains security configuration files.
    - **SecurityConfig.java**: Security settings and filters.
  - **controller**: Contains REST controllers.
    - **TokenController.java**: Handles token generation requests.
    - **dto**: Contains Data Transfer Objects.
      - **TokenRequestDto.java**: DTO for token request parameters.
      - **TokenResponseDto.java**: DTO for token response.
  - **domain**: Contains domain entities.
    - **AccessToken.java**: Represents the access token entity.
    - **Client.java**: Represents the client entity.
  - **exception**: Contains custom exceptions.
    - **InvalidClientCredentialsException.java**: Exception for invalid client credentials.
  - **infrastructure/persistence**: Contains persistence implementations.
    - **InMemoryClientRepository.java**: In-memory client repository.
  - **repository**: Contains repository interfaces.
    - **ClientRepository.java**: Interface for client data access.
  - **service**: Contains service interfaces and implementations.
    - **TokenService.java**: Interface for token generation service.
    - **impl**: Contains service implementations.
      - **TokenServiceImpl.java**: Implements token generation logic.

- **src/main/resources**: Contains application resources.
  - **application.properties**: Configuration properties for the application.
  - **static**: Directory for serving static files.
  - **templates**: Directory for serving template files.

- **src/test/java/com/example/sts**: Contains unit tests.
  - **StsApplicationTests.java**: Tests for the main application class.
  - **controller**: Contains tests for controllers.
    - **TokenControllerTest.java**: Tests for the TokenController.
  - **service/impl**: Contains tests for service implementations.
    - **TokenServiceImplTest.java**: Tests for the TokenServiceImpl.

- **pom.xml**: Maven configuration file for project dependencies and build settings.

## Setup Instructions

1. Clone the repository.
2. Navigate to the project directory.
3. Build the project using Maven:
   ```
   mvn clean install
   ```
4. Run the application:
   ```
   mvn spring-boot:run
   ```
5. Send a POST request to `/token` with the required parameters to generate a token.

## Usage

To generate a token, send an HTTP POST request to the `/token` endpoint with the following form URL encoded parameters:

- `grant_type`: The type of grant being requested.
- `client_id`: The ID of the client.
- `client_secret`: The secret associated with the client.

On success, the response will include:

- `access_token`: The generated access token.
- `token_type`: The type of the token.
- `expires_in`: The expiration time of the token.
- `refresh_token`: The refresh token.
- `scope`: The scope of the token.
- `active`: Indicates if the token is active.

## License

This project is licensed under the MIT License.