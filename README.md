# Blog API

Welcome to the Blog API project! This backend API is developed to power a highly secure blog application, leveraging Spring Security, Spring Boot, and end-to-end encryption protocols. It ensures the privacy of user data and maintains data integrity throughout the system.

## Technologies Used

- **Java**
- **Spring Boot**
- **MySQL**
- **Spring Data JPA**
- **Swagger**

## Project Overview

Developed and executed a highly secure back-end API for a blog application, leveraging Spring Security, Spring Boot, and end-to-end encryption protocols, safeguarding user privacy and ensuring data integrity.

## Getting Started

To get started with the Blog API, follow these steps:

1. Clone the repository:

    ```bash
    https://github.com/jhantu626/Content-Management-API.git
    ```

2. Navigate to the project directory:

    ```bash
    cd blog-app-apis
    ```

3. Build the project:

    ```bash
    mvn clean install
    ```

4. Run the application:

    ```bash
    java -jar target/blog-app-apis.jar
    ```

## Technology Stack

- **Java**: The programming language used for the backend development.
- **Spring Boot**: A framework for building Java-based enterprise applications.
- **MySQL**: The relational database management system used for data storage.
- **Spring Data JPA**: Simplifies data access with the Spring applications using the JPA (Java Persistence API).
- **Swagger**: Provides a user interface to access and test the API.

## API Documentation

Explore and test the API using Swagger documentation available [here](http://localhost:8080/swagger-ui.html).

## Configuration

### Database Configuration

Configure your MySQL database connection details in the `application.properties` file.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
spring.datasource.username=your_username
spring.datasource.password=your_password
