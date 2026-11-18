
# Orders API

## Overview
A RESTful Orders API built using Java and Quarkus.
It supports creating orders, retrieving all orders and
retrieving individual orders by ID.

## Tech Stack
- Java 25
- Quarkus
- Maven
- REST Assured
- PostgreSQL 18
- Docker and Docker Compose

## Prerequisites
- JDK 25
- Docker Desktop
- Git

## Database Setup

Start PostgreSQL using Docker Compose:

```bash
docker compose up -d
```

Check the database container:

```bash
docker ps
```

Stop the database:

```bash
docker compose down
```

## Running the Application

On Windows:

```powershell
.\mvnw.cmd quarkus:dev
```

The application runs at:

http://localhost:8080

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| GET | /orders | Get all orders |
| GET | /orders/{id} | Get order by ID |
| POST | /orders | Create an order |

## Running Tests

Windows:

```powershell
.\mvnw.cmd test
```
