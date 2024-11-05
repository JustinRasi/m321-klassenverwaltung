# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the project's JAR file into the container at /app
COPY target/m321_klassenverwaltung-0.0.1-SNAPSHOT.jar /app/m321_klassenverwaltung.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "m321_klassenverwaltung.jar"]