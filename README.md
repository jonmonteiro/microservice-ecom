## About the Project

This project is a **Stock Manager System** built with a **microservices architecture** using **Java Spring Boot** and **Apache Kafka**:

- **Auth Service** – Handles user authentication and authorization.
- **Sales Service** – Manages product creation and sales operations.
- **Stock Service** – Manages product inventory.

## Auth Endpoints

````bash
POST /auth/login - Login into the App

POST /auth/register - Register a new user into the App
````

## Sale Endpoints

````bash
POST /api/products - Register a new product (ADMIN access required).
````

## Stock Endpoints
````bash
GET /api/products - Retrieve a list of all products.
````

## Running the project

Make sure to run the following commands:

### Create Containers

```bash
docker-compose up -d
```

### Create Topic
```bash
 docker exec kafka kafka-topics --create --topic product-topic --bootstrap-server kafka:9092 --partitions 1 --replication-factor 1
```
###
- Run all microservices

---

