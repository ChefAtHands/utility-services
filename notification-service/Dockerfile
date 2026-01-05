FROM eclipse-temurin:19-jre-alpine
WORKDIR /app
COPY target/notification-service-0.1.0-SNAPSHOT.jar app.jar
EXPOSE 8087
ENTRYPOINT ["java", "-jar", "app.jar"]