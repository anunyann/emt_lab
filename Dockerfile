FROM maven:3.9.2-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
COPY src/main/resources/application.properties src/main/resources/
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8181
ENTRYPOINT ["java", "-jar", "app.jar"]

