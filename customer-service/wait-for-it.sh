#!/bin/sh
echo "********************************************************"
echo "Waiting for the config server to start on port $CONFIG_PORT"
echo "********************************************************"
while ! `nc -z  config-server $CONFIG_PORT`; do sleep 3; done
echo "*******  Config Server has started"

echo "********************************************************"
echo "Starting customer-service "
echo "********************************************************"
java -Xmx400m   -Djava.security.egd=file:/dev/./urandom -Djasypt.encryptor.password=altenpass@admin -jar  /usr/app/customer-service.jar
