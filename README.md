# Event-Driven Food Delivery System

A backend-only food delivery platform built using Spring Boot microservices, Apache Kafka, PostgreSQL, Redis, JWT Authentication, and API Gateway. The system follows an event-driven architecture for asynchronous order and payment processing.

---

# Features

## Authentication & Security

* JWT Authentication
* Spring Security
* Role-Based Authorization
* Protected APIs
* Centralized Authentication via User Service

## Restaurant Management

* Add Restaurant
* View Restaurants
* Update Restaurant Details
* Delete Restaurant
* Redis Caching for Faster Reads

## Order Management

* Place Orders
* View Orders
* Track Order Status
* Event Publishing using Kafka

## Payment Processing

* Simulated Payment Processing
* Payment Success & Failure Handling
* Kafka-Based Event Communication
* Dead Letter Queue (DLQ) Support

## Notification Management

* Consume Kafka Events
* Generate Notifications for Orders
* Generate Notifications for Payment Updates
* Store Notification History

## Backend Features

* API Gateway
* Microservices Architecture
* Separate Database per Service
* Apache Kafka Integration
* Redis Caching
* Global Exception Handling
* DTO-Based Communication
* Swagger/OpenAPI Documentation

---

# Architecture

## Microservices

* API Gateway
* User Service
* Restaurant Service
* Order Service
* Payment Service
* Notification Service
* Common Library

## Event Flow

User Places Order

↓

Order Service

↓ (OrderCreatedEvent)

Kafka

↓

Payment Service

↓ (PaymentSuccessEvent / PaymentFailedEvent)

Kafka

↓

Order Service

↓

Notification Service

---

# Tech Stack

## Backend

* Java 17
* Spring Boot 3
* Spring Security
* Spring Data JPA
* Hibernate
* PostgreSQL
* Apache Kafka
* Redis
* JWT
* Maven
* Swagger/OpenAPI

---

# Project Structure

```text
event-driven-food-delivery-system

├── api-gateway
├── user-service
├── restaurant-service
├── order-service
├── payment-service
├── notification-service
├── common-lib
├── screenshots
└── README.md
```

---

# Service Ports

| Service              | Port |
| -------------------- | ---- |
| API Gateway          | 9090 |
| User Service         | 8081 |
| Restaurant Service   | 8082 |
| Order Service        | 8083 |
| Payment Service      | 8084 |
| Notification Service | 8085 |

---

# Kafka Topics

| Topic           |
| --------------- |
| order-created   |
| payment-success |
| payment-failed  |

---

# API Endpoints

## Authentication APIs

| Method | Endpoint           |
| ------ | ------------------ |
| POST   | /api/auth/register |
| POST   | /api/auth/login    |

## Restaurant APIs

| Method | Endpoint              |
| ------ | --------------------- |
| POST   | /api/restaurants      |
| GET    | /api/restaurants      |
| GET    | /api/restaurants/{id} |
| PUT    | /api/restaurants/{id} |
| DELETE | /api/restaurants/{id} |

## Order APIs

| Method | Endpoint         |
| ------ | ---------------- |
| POST   | /api/orders      |
| GET    | /api/orders      |
| GET    | /api/orders/{id} |

## Notification APIs

| Method | Endpoint                           |
| ------ | ---------------------------------- |
| GET    | /api/notifications                 |
| GET    | /api/notifications/order/{orderId} |

---

# Database Design

Each microservice maintains its own PostgreSQL database.

## Databases

* user_db
* restaurant_db
* order_db
* payment_db
* notification_db

---

# Redis Caching

Restaurant details are cached using Redis to reduce database hits and improve response times.

---

# Dead Letter Queue (DLQ)

Failed Kafka messages are retried and redirected to a Dead Letter Queue for further analysis, improving system reliability and fault tolerance.

---

# Swagger Documentation

Swagger UI URLs

```text
http://localhost:8081/swagger-ui/index.html
http://localhost:8082/swagger-ui/index.html
http://localhost:8083/swagger-ui/index.html
http://localhost:8084/swagger-ui/index.html
http://localhost:8085/swagger-ui/index.html
```

---

# Installation & Setup

## Clone Repository

```bash
git clone https://github.com/saarv3sh7/event-driven-food-delivery-system.git
```

## Start PostgreSQL

Create databases:

```sql
CREATE DATABASE user_db;
CREATE DATABASE restaurant_db;
CREATE DATABASE order_db;
CREATE DATABASE payment_db;
CREATE DATABASE notification_db;
```

## Start Kafka

Ensure Kafka broker is running on:

```text
localhost:9092
```

## Start Redis

```text
localhost:6379
```

## Build Project

```bash
mvn clean install
```

## Run Services

Start services in the following order:

```text
1. User Service
2. Restaurant Service
3. Order Service
4. Payment Service
5. Notification Service
6. API Gateway
```

---

# Author

Sarvesh Yeutkar

M.Tech Computer Science & Information Security

College of Engineering Pune (COEP)
