FROM java:8-jdk-alpine

RUN apk update && apk add bash

COPY ./target/vehicle-service-0.0.1-SNAPSHOT.jar /usr/app/vehicle-service.jar

WORKDIR /usr/app


COPY ./wait-for-it.sh wait-for-it.sh 

RUN chmod +x wait-for-it.sh


CMD ./wait-for-it.sh

EXPOSE 8091