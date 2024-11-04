# E-Commerce API Microservices

This project is an e-commerce API system with three microservices: **User Service**, **Order Service**, and **Product Service**. The services communicate with each other via REST APIs. Each microservice is designed to handle specific functionality, and they are set up as individual Spring Boot projects.

## Table of Contents

- [Architecture Overview](#architecture-overview)
- [Services](#services)
    - [User Service](#user-service)
    - [Order Service](#order-service)
    - [Product Service](#product-service)
- [Technologies Used](#technologies-used)
- [Pre-requisites](#pre-requisites)
- [Docker Image Creation](#Docker-Image-Creation)
- [Endpoints](#endpoints)

## Architecture Overview

Each microservice performs the following functions:

- **User Service**: Manages user accounts (CRUD operations) using an in-memory H2 database.
- **Order Service**: Manages orders placed by users (CRUD operations) and stores them in a relational database (MySQL).
- **Product Service**: Manages product listings (CRUD operations) stored in a NoSQL database (MongoDB).

All services communicate via REST APIs, where:
1. the User Service interacts with the Order Service and Product Service to verify Order details and finding all products.
2. Order Service is interacts with User service for user verification and product service for verify product
3. product service is not interacting with other service and in future, I will apply spring security so only admin of company have access to this service, directly user cannot interact with it.

## Services

### User Service

- **Port**: 8081
- **Database**: H2 (In-memory)
- **Functionality**: User management (CRUD operations)

### Order Service

- **Port**: 8082
- **Database**: MySQL
- **Functionality**: Order management (CRUD operations)
- **Inter-service Communication**: Calls User Service to verify user details.

### Product Service

- **Port**: 8083
- **Database**: MongoDB
- **Functionality**: Product management (CRUD operations)

## Technologies Used

- **Java**: 21
- **Spring Boot**: 3.3.0
- **Databases**:
    - H2 (In-memory) for User Service
    - MySQL for Order Service
    - MongoDB for Product Service
- **Tools**:
    - Gradle (for dependencies)
    - Docker (optional, for containerization)
    - Postman (for testing)

## Pre-requisites

1. **Java 21** or higher
2. **MySQL** for Order Service
3. **MongoDB** for Product Service
4. **Gradle** to build the projects
5. **Docker** (optional, for containerization)
6. **Postman** or **curl** for API testing

## Docker Image Creation

**Note**: Dockerfiles and related scripts are already included for each service. Follow these steps to create and run Docker images.

### Steps

1. **Install Docker**
    - Ensure Docker is installed on your system. You can download it from [Docker's official website](https://www.docker.com/get-started).

2. **Build Docker Images**
    - Navigate to each service directory (`user-service`, `order-service`, `product-service`) and run the following command to create Docker images.

      ```bash
      docker build -t user-service:1.0.0 .
      ```

    - Replace `user-service` with `order-service` and `product-service` for the other services.

3. **Push Docker Images**
    - To deploy on a cloud platform (e.g., AWS EC2), push the images to a container registry such as Docker Hub, AWS ECR, or any other registry.

      Example:
      ```bash
      docker tag user-service:1.0.0 your-dockerhub-username/user-service:1.0.0
      docker push your-dockerhub-username/user-service:1.0.0
      ```

4. **Run Docker Containers**
    - Run each service using the following command, replacing `<imageId>` with the actual image ID or tag (e.g., `user-service:1.0.0`).

      ```bash
      docker run -p 8081:8081 -d user-service:1.0.0
      ```

    - **Repeat** for each service, updating the port and image ID:
        - **User Service**: `docker run -p 8081:8081 -d user-service:1.0.0`
        - **Order Service**: `docker run -p 8082:8082 -d order-service:1.0.0`
        - **Product Service**: `docker run -p 8083:8083 -d product-service:1.0.0`

5. **Repeat Steps 2-4 for Each Service**
    - Build, tag, push, and run each service (`user-service`, `order-service`, `product-service`) as described.

---

After following these steps, all services should be running in Docker containers and accessible via their respective ports.

### Setup Instructions

### 1. Clone the Repository

```bash
https://github.com/amitkryadavsachin/akart-service.git

User Service: No additional setup is needed, as it uses an in-memory H2 database.
Order Service: Set up a MySQL or PostgreSQL database. Update the application.properties or application.yml file in the Order Service with your database credentials.
Product Service: Set up a MongoDB instance and update the application.properties or application.yml file in the Product Service with your MongoDB URI.
Configure Ports

Ensure each service runs on a unique port. Ports can be configured in each service’s application.properties.
Build the Project

bash
Copy code
./gradlew build
Running the Services
To run each service, open separate terminal windows, navigate to each service directory, and use the following command:

bash
Copy code
# For User Service
./gradlew :user:bootRun

# For Order Service
./gradlew :order:bootRun

# For Product Service
./gradlew :product:bootRun
Each service will be accessible on the following default ports unless specified otherwise:

User Service: http://localhost:8081
Order Service: http://localhost:8082
Product Service: http://localhost:8083
Testing the Services
Postman or cURL can be used to interact with the endpoints.
Verify the services can communicate by testing a workflow (e.g., placing an order via the Order Service and checking that it verifies user details through the User Service).
Test each endpoint to ensure all CRUD operations work as expected.

I have added dependency springdoc-openapi for api documentation so
Endpoints you can view through baseUrl+swagger-ui/index.html

Docker Image Creation
To containerize each service, follow these steps:

Install Docker: Ensure Docker is installed and running.

Build Docker Images

Navigate to each service directory and run:
bash
Copy code
docker build -t user:1.0.0 -f Dockerfile .
docker build -t order:1.0.0 -f Dockerfile .
docker build -t product:1.0.0 -f Dockerfile .
Run Docker Containers

Start each service by running:
bash
Copy code
docker run -p 8081:8081 -d user:1.0.0
docker run -p 8082:8082 -d order:1.0.0
docker run -p 8083:8083 -d product:1.0.0
Push Docker Images (Optional)

If deploying to a cloud environment, push the images to a container registry like Docker Hub or AWS ECR:
bash
Copy code
docker tag user-service:1.0.0 your-repo/user-service:1.0.0
docker push your-repo/user-service:1.0.0
Repeat for other services.

use branch: master