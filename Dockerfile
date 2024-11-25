FROM openjdk:17-jdk-slim

WORKDIR /app

COPY build/libs/*.jar app.jar

EXPOSE 4000

ENV DB_HOST=postgres \
    DB_PORT=5432 \
    POSTGRES_DB=postgres \
    POSTGRES_USER=postgres \
    POSTGRES_PASSWORD=postgrespassword \
    SERVER_PORT=4000

ENTRYPOINT ["java", "-jar", "app.jar"]
