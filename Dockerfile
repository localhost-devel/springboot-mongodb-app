FROM openjdk:8-alpine

RUN apk update && apk add /bin/sh

RUN mkdir -p /opt/app
ENV PROJECT_HOME=/opt/app

COPY target/springboot-mongodb-application-1.0.jar $PROJECT_HOME/springboot-mongodb-application.jar

WORKDIR $PROJECT_HOME
EXPOSE 8080
CMD ["java" ,"-jar","./springboot-mongodb-application.jar"]