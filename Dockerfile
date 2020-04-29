FROM openjdk:8-jdk-alpine

MAINTAINER Abdur-Rub

COPY  /target/employee-0.0.1-SNAPSHOT.jar .

CMD["java -jar employee-0.0.1-SNAPSHOT.jar"]
