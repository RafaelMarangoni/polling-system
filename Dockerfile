FROM ubuntu:latest AS build

RUN apt-get update && \
    apt-get install -y openjdk-17-jdk maven

WORKDIR /app

COPY . .

RUN ls -la && \
    pwd && \
    mvn clean install

FROM openjdk:17-jdk-slim

EXPOSE 9081

COPY --from=build /app/target/polling-system-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]