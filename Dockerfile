# Stage 1 - Building JAR (Java Application Runtime)
FROM maven:3.8.3-openjdk-17 AS builder

WORKDIR /app

COPY . /app

RUN mvn clean install -DskipTests=true


#---------------
# Stage 2 - Run JAR
FROM openjdk:17-alpine

WORKDIR /app

COPY --from=builder /app/target/*.jar /app/taget/ProductService.jar

CMD ["java", "-jar", "/app/taget/ProductService.jar"]

