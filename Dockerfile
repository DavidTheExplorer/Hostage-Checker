FROM maven:3.8.3-amazoncorretto-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM amazoncorretto:17-alpine-jdk
COPY --from=build target/*.jar app.jar
EXPOSE 8080

CMD ["java", "-jar", "app.jar"]