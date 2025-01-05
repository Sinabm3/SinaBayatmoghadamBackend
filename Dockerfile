# Step 1: Build Stage
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:resolve

# Copy the source code and build the application
COPY src ./src
RUN mvn clean package -DskipTests

# Step 2: Run Stage
FROM eclipse-temurin:21-jre

# Set the working directory
WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your app runs on
EXPOSE 8080

# Define the entry point
ENTRYPOINT ["java", "-jar", "app.jar"]
