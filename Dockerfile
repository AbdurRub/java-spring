
#  java image
FROM openjdk:8-jre-alpine
#  maven image
FROM maven:3.5-jdk-8-alpine

MAINTAINER Abdur-Rub

WORKDIR /app
# cloning bitbucket repo
RUN git clone -b bamboo-development-plan-abdurrub https://abdurrub:nbs00591@bitbucket.org/northbay/cdk-train.git

# Running mvn step to make jar file
RUN mvn package

WORKDIR /app
COPY --from=1 /app/target/employee-0.0.1-SNAPSHOT.jar /app

