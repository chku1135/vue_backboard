# Start with a stable Java 17 image
FROM eclipse-temurin:17-jre-jammy

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Add the application's jar to the container
COPY target/*.jar app.jar

# Run the jar file with prd profile explicitly
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=prd", "-jar","/app.jar"]
