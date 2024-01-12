# Use an official OpenJDK runtime as a parent image
FROM openjdk:17

# Set the working directory inside the container
WORKDIR /app

# Copy the application JAR file into the container
COPY build/libs/dbdemo-0.0.1-SNAPSHOT.jar app.jar

# Expose the port that the application will run on
EXPOSE 9001

# Specify the command to run on container startup
CMD ["java", "-jar", "app.jar"]

