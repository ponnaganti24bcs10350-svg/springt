# Student Attendance System

A robust backend API designed to streamline student attendance tracking, course management, and user administration. Built with Spring Boot and secured using JWT authentication.

## 🚀 Features

*   **User Management**: Role-based access control (Admin, Teacher, Student).
*   **Secure Authentication**: Stateless authentication using JSON Web Tokens (JWT).
*   **Course Management**: Create and manage courses with unique codes.
*   **Attendance Tracking**: Mark and monitor attendance with status (Present, Absent, etc.).
*   **Data Integrity**: Prevents duplicate attendance entries and ensures referential integrity.
*   **API Documentation**: Integrated OpenAPI (Swagger) documentation.

## 🛠️ Tech Stack

*   **Language**: Java 17
*   **Framework**: Spring Boot 3.5.0
*   **Database**: PostgreSQL
*   **Security**: Spring Security 6
*   **Build Tool**: Maven
*   **Documentation**: SpringDoc OpenAPI (Swagger UI)

## 📋 Prerequisites

*   Java Development Kit (JDK) 17 or higher
*   PostgreSQL installed and running
*   Maven (wrapper included)

## ⚙️ Configuration

The application is configured via `src/main/resources/application.properties`.

**Default Database Settings:**
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/attendance_db
spring.datasource.username=postgres
spring.datasource.password=71421
```
*Note: Update these values to match your local PostgreSQL configuration.*

## 🏃‍♂️ Getting Started

1.  **Clone the repository**
    ```bash
    git clone <repository-url>
    cd Student-Attendance-System
    ```

2.  **Set up the Database**
    Create a PostgreSQL database named `attendance_db`.
    ```sql
    CREATE DATABASE attendance_db;
    ```

3.  **Run the Application**
    Using Maven Wrapper:
    ```bash
    ./mvnw spring-boot:run
    ```

4.  **Access the Application**
    The server will start on port `8080`.

## 📚 API Documentation

Once the application is running, you can explore and test the APIs using the interactive Swagger UI:

👉 **[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)**

### Key Endpoint flows:
1.  **Auth**: POST `/api/auth/register` (Teacher/Student) -> POST `/api/auth/login` -> Get Token.
2.  **Courses**: POST `/api/courses` (Requires Token).
3.  **Attendance**: POST `/api/attendance` (Requires Token).

## 🧪 Testing

You can test the application using:
*   **Swagger UI**: (Recommended) Direct browser interaction.
*   **Postman**: Import the collection or manually hit endpoints using the Bearer Token from login.

## 📂 Project Structure

```
src/main/java/com/example/attendance
├── config       # Security and App configurations
├── controller   # REST API Endpoints
├── dto          # Data Transfer Objects
├── model        # JPA Entities (Database Schema)
├── repository   # Database Access Layer
└── service      # Business Logic
```
