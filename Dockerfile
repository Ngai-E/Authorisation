# syntax=docker/dockerfile:1

FROM openjdk:11
WORKDIR /auth

COPY ./target/*.jar auth.jar
COPY ./src/main/resources/*.properties application.properties
COPY ./src/main/resources/*.xml log4j2.xml

CMD ["java", "-jar", "auth.jar"]