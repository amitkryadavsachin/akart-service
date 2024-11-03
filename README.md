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


### 1. Clone the Repository

```bash
https://github.com/amitkryadavsachin/akart-service.git

use branch: master