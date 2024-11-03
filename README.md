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
- [Setup Instructions](#setup-instructions)
- [Running the Services](#running-the-services)
- [Testing the Services](#testing-the-services)
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

## Setup Instructions

### 1. Clone the Repository

```bash
https://github.com/amitkryadavsachin/akart-service.git

use branch: master