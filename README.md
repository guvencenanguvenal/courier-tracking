
# Courier Tracker

## Getting Started

    - There is 1 controller (Courier)
    - You can check controller with Swagger (http://localhost:8080/swagger-ui/index.html)

## Tech Stack

    - docker compose (please check docker-compose.yml)
    - Postgres
    - REST Api developed with Spring Boot.
    - Open API Swagger 3 (with default config)
    - mapping library ModelMapper (encupsulated with ModelUtil service)

### Some Design Patterns That I Use In This Project

    - Observer pattern for tracking Courier location log.
    - Factory pattern for creating CourierTracker
    - Singleton pattern for Store entity.

# Usage

    - Please check Postman collection.
    - First you must create a courier.
    - You can get all saved courier with getAll api.
    - You can check distance notification system with this test method 
            whenUpdateCourierLocation_withValidLocation_shouldLogNearlyStores

## Running

    docker-compose up
