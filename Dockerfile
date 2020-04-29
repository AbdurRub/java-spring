
#  java image
FROM openjdk:8-jre-alpine
#  maven image
FROM maven:3.5-jdk-8-alpine

MAINTAINER Abdur-Rub

WORKDIR  /app
COPY  /target/employee-0.0.1-SNAPSHOT.jar /app

CMD["java -jar /app/employee-0.0.1-SNAPSHOT.jar"]
