# Use the official OpenJDK image as the base image
FROM openjdk:11-jre-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/your-application.jar app.jar

# Set the command to run the application when the container starts
CMD ["java", "-jar", "app.jar"]

