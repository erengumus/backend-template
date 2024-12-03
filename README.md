
# Spring Boot Backend Template

# Description
This project is a backend template application developed using **Spring Boot 3**. It includes features such as **dynamic role management**, **JWT-based authentication**, **multilingual support**, and **session user management**. **Liquibase** is used to manage database changes.

# Technologies and Tools

- **Java**: Primary programming language for the backend.
- **Spring Boot 3**: Framework for developing RESTful APIs.
- **Spring Security**: Authentication and authorization.
- **MapStruct**: DTO and Entity conversion.
- **Liquibase**: Database schema management.
- **PostgreSQL**: Database.
- **Spring Data JPA**:  JPA for database operations.
- **Redis**: Cache management.
- **JWT (JSON Web Token)**: Token-based user session security.
- **Slf4j**: Logging framework.
- **Maven**: Project management and dependency configuration.

# Features

- **JWT-Based Authentication**  
  Token generation and validation for user logins.

- **Dynamic Role Management**  
  Roles (e.g., `ADMIN`, `USER`) are stored in a table and assigned dynamically to users.

- **Liquibase for Database Management**  
  Database schema is managed using Liquibase in SQL format.

- **Multilingual Support**  
  Error and informational messages are defined in `messages.properties` files.

- **Session Management**  
  User information can be retrieved from the session.

- **Logging**  
  All actions are logged at `DEBUG` and `INFO` levels.

# API Endpoints

## Authentication

- **POST** `/api/auth/register`  
  Register a new user.

- **POST** `/api/auth/login`  
  User login.

## User Operations

- **GET** `/api/auth/user`  
  Fetch user information.

- **GET** `/api/auth/welcome`  
  User-specific welcome message.

- **GET** `/api/auth/checkLocale`  
  Check locale.

# Setup

## 1. **Install Dependencies:**
To get started, you need to install the necessary dependencies.

   ```bash
   mvn clean install
   ```

## 2. **Run Liquibase for Database Setup:**
Set up your database schema using Liquibase.

   ```bash
   mvn liquibase:update
   ```

## 3. **Start the Application:**
Finally, run the application.

   ```bash
   mvn spring-boot:run
   ```

# Project Structure

```plaintext
src/main
├── java/com/template/backendtemplate
│   └── core
│      ├── auth         # Authentication and role management
│      ├── config       # Spring Security and JWT configurations
│      ├── entity       # Base Entity
│      ├── exception    # Global Exception Handler
│      ├── filter       # JWT filters
│      ├── listener     # Auditable Entity Listener
│      ├── messages     # Multilingual support
│      ├── model        # JWT Models
│      ├── service      # Session and message services
│      └── utils        # Utility classes (e.g., JwtUtil)
│   
│
├── resources
│   ├── db/changelog        # Liquibase changelog files
│   ├── messages            # Multilingual files
│   ├── application.yml     # Configuration
│   └── docker-compose.yml  # Configuration for local PostgreSQL and Redis
```

# Installation & Running

## Requirements:
- JDK 17 or higher
- Maven
- PostgreSQL database
- Redis (optional, for caching)

## Setup Instructions:
1. Clone this repository.
   ```bash
   git clone https://github.com/erengumus/backend-template.git
   ```

2. Navigate to the project directory.
   ```bash
   cd backend-template
   ```

3. Install dependencies and run the application:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

# License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
