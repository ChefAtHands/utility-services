FROM eclipse-temurin:19-jre-alpine
WORKDIR /app
COPY target/favourites-service-0.1.0-SNAPSHOT.jar app.jar
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "app.jar"]