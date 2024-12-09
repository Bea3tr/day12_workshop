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

# Build the application (chmod - to add executable)
# RUN chmod a+x ./mvnw && ./mvnw package -Dmaven.test.skip=true
# Remove source code
# RUN rm -r src
RUN ./mvnw package -Dmaven.test.skip=true

# If build is successful, then the jar is in 
# ./target/day12-0.0.1-SNAPSHOT.jar

## How to run the application
ENV SERVER_PORT=8080 
# For railway
# ENV PORT=8080

# What port does the application need
EXPOSE ${SERVER_PORT}
# EXPOSE ${PORT}

# Run the application
# ENTRYPOINT SERVER_PORT=${PORT} java -jar target/day12_workshop-0.0.1-SNAPSHOT.jar
ENTRYPOINT java -jar target/day12_workshop-0.0.1-SNAPSHOT.jar

