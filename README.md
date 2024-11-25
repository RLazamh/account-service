# Account-Service

This repository contains the implementation of the `account-service` microservice. This service manages account data, including CRUD operations, data validation, and error handling. It is part of a broader architecture that adheres to clean code principles for maintainability and scalability. The deployment options include running locally or within Docker containers.

## Project Structure Overview

This project follows a layered architecture, ensuring separation of concerns and easy extensibility. It includes the following layers:

- **Domain Layer**: Contains the core business logic and entities.
- **Application Layer**: Implements the use cases for account management.
- **Infrastructure Layer**: Handles database interactions using JPA.
- **Presentation Layer**: Manages incoming HTTP requests and routes them to the appropriate handlers.

## Deployment

To deploy and run the `account-service` microservice, you have two flexible options:

- **Docker Containers**: Ideal for a seamless and isolated runtime environment.
- **Local Setup**: Perfect for development and debugging directly on your machine, while still using Docker for infrastructure services.

### Prerequisites
Before proceeding, make sure the following are installed and configured:

- **Docker** and **Docker Compose**
- **JDK 17**

### Running with Docker Containers

To deploy the `account-service` microservice using Docker containers, follow these steps:

#### Step 1: Set up the infrastructure
1. Download the `docker-compose-infra.yml` file from the link below and place it in the root directory of your project:
    - [Docker Compose Infra File](https://drive.google.com/file/d/1Bg2flsdO9lvctRw8aGa2FfUuQln8GULX/view?usp=sharing)

2. Run the following command to start the required infrastructure services (e.g., PostgreSQL):
   ```bash
   docker-compose -f docker-compose-infra.yml up -d
   docker network create bank_network
   docker network connect bank_network postgres
   ```

#### Step 2: Build and Run the `account-service` Docker container
1. In the root directory of the `account-service` microservice, execute the following commands:
   ```bash
   # Build the project using Gradle
   ./gradlew clean build

   # Ensure any previous Docker containers and volumes are removed
   docker-compose down --volumes --remove-orphans

   # Build and start the Docker containers
   docker-compose up --build
   ```

2. Once the container is running, the service will be available at:
   [http://localhost:4000/account](http://localhost:4000/account)

---

### Running Locally

To run the `account-service` microservice locally, follow these steps:

1. Make sure you are on the `developer` branch:
   ```bash
   git checkout developer
   ```

2. In the root directory of the `account-service` microservice, execute the following commands to build and run the application:
   ```bash
   # Build the project using Gradle
   ./gradlew clean build

   # Run the application locally
   ./gradlew bootRun
   ```

3. Once the application is running, it will be accessible at:
   [http://localhost:4000/account](http://localhost:4000/account)

---

## Postman Collection

You can find the Postman collection for testing the `account-service` endpoints at the link below:

- [Bank Account Collection](https://drive.google.com/file/d/1hAEypEhgeMO39hyL-_cVRaoeNP6SlkGQ/view?usp=sharing)
- [Bank Transaction Collection](https://drive.google.com/file/d/1UI6PnBrR8kHkmSO9b40usrPQfvUBXmzL/view?usp=sharing)

---

## AccountController

The `AccountController` is a Spring Boot REST controller that exposes CRUD operations for managing account data. It acts as an intermediary between the API requests and the business logic, delegating the actual operations to the `CrudAccountUseCase`.

#### Endpoints:
- **POST /account**:
    - Creates a new account.
    - Takes an `AccountDTO` as input and returns the created account.

- **GET /account/{id}**:
    - Retrieves a specific account by its ID.
    - Returns the account data in the form of an `AccountDTO`.

- **GET /account**:
    - Retrieves a list of all accounts.
    - Returns the accounts as a list of `AccountDTO` objects.

---

## TransactionController

The `TransactionController` is a Spring Boot REST controller responsible for managing transactions related to accounts. It provides endpoints to register transactions, query transactions, and generate reports.

#### Endpoints:
- **POST /transaction**:
    - Registers a new transaction.
    - Takes a `TransactionDTO` as input and returns the registered transaction.

- **GET /transaction**:
    - Retrieves all transactions.
    - Returns a list of `TransactionDTO` objects.

- **GET /transaction/{transactionId}**:
    - Retrieves a specific transaction by its ID.
    - Returns the transaction data in the form of a `TransactionDTO`.

- **GET /transaction/by-user**:
    - Retrieves transactions for a specific user within a date range.
    - Takes `clientId`, `startDate`, and `endDate` as query parameters and returns a list of `TransactionDTO` objects.

- **GET /transaction/report**:
    - Generates a report for a specific user within a date range.
    - Takes `clientId`, `startDate`, and `endDate` as query parameters and returns a `ReportDTO`.

---
All rights reserved © 2024 Roger Laza. Unauthorized use, distribution, or reproduction of any part of this material is strictly prohibited.
