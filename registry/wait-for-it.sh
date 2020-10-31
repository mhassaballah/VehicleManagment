#!/bin/sh

# uncomment the below lines during use docker-compose

#echo "********************************************************"
#echo "Waiting for the configuration server to start on port $CONFIGSERVER_PORT"
#echo "********************************************************"
#while ! `nc -z config $CONFIGSERVER_PORT`; do sleep 3; done
#echo "*******  Configuration Server has started"

echo "********************************************************"
echo "Starting registry-service "
echo "********************************************************"
java -Xmx400m  -Djava.security.egd=file:/dev/./urandom -Djasypt.encryptor.password=altenpass@admin -jar  /usr/app/registry.jar
