# Drone API

It is a backend application in Java that register a drone with specific medications depends on medication weight and drone battery.

## Run 
```
mvn spring-boot:run
```
The home page can be access in http://localhost:8080.

## Docker Image 

Create project jar:
```
mvn clean install
```

Build Docker image:
```
docker build -t drone .
```

Run Docker image:
```
docker run -d -p 8080:8080 --name drone-container drone:latest

```

## Technologies
Below the technologies used in this project:

* Java 8 - programming language (current long-term support release version).
* Spring Boot Data JPA - Used to access SQlite data.
* SQlite - drone database.
* MockMVC - Template tests.
* JUnit5 - Unit tests.
* Lombok - Avoid boilerplate java code.
* MapStruct - Avoid boilerplate copy values code.
* Docker - Create a file for build a project image.
