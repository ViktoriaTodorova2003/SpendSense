# Use OpenJDK as base image
FROM openjdk:17-jdk-slim

# Set working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
#COPY target/expense-service.jar app.jar
COPY budget-service-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port (change this based on your app's config)
EXPOSE 7070

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
#ENTRYPOINT ["jshell"]