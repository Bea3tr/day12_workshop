# Imagine Dockerfile is your notebook
# Install java, must be first line
FROM eclipse-temurin:23-jdk

LABEL maintainer="bea3tr"

## How to build the application

# Create a directory /app & change directory into /app
# Outside of /app
WORKDIR /app

# Inside /app
# Copy files over: src destination; . -> to same directory; e.g. src src (directory must give name)
COPY mvnw .
COPY .mvn .mvn

COPY pom.xml .
COPY src src

# Build the application
RUN ./mvnw package -Dmaven.test.skip=true

# If build is successful, then the jar is in 
# ./target/day12-0.0.1-SNAPSHOT.jar

## How to run the application
ENV SERVER_PORT=8080

# What port does the application need
EXPOSE ${SERVER_PORT}

# Run the application
ENTRYPOINT java -jar target/day12_workshop-0.0.1-SNAPSHOT.jar

