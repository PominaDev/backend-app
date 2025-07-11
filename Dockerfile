# Build stage
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app
COPY . .

RUN mvn clean install -DskipTests

# Run stage
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /run
COPY --from=build /app/target/erp-app-0.0.1-SNAPSHOT.jar /run/pomina.jar

EXPOSE 8080

CMD ["java", "-jar", "/run/pomina.jar"]