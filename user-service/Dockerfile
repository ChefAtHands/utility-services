FROM eclipse-temurin:19-jre-alpine
WORKDIR /app
COPY target/user-service-0.1.0-SNAPSHOT.jar app.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "app.jar"]