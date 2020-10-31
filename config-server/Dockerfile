#FROM maven:latest as builder

#COPY . /usr/local/config-server

#WORKDIR /usr/local/config-server

#RUN mvn clean install

FROM java:8-jdk-alpine

COPY ./target/config-server-0.0.1-SNAPSHOT.jar /usr/app/config-server.jar

WORKDIR /usr/app

CMD ["java", "-Xmx400m", "-jar","-Djava.security.egd=file:/dev/./urandom","-Djasypt.encryptor.password=altenpass@admin", "/usr/app/config-server.jar"]

EXPOSE 8051