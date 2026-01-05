FROM eclipse-temurin:19-jre-alpine
WORKDIR /app
COPY target/ingredient-service-0.1.0-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]