#!/bin/sh


echo "********************************************************"
echo "Waiting for the config server to start on port $CONFIG_PORT"
echo "********************************************************"
while ! `nc -z  config-server $CONFIG_PORT`; do sleep 3; done
echo "*******  Config Server has started"

echo "********************************************************"
echo "Waiting for the vehicle service to start on port $VEHIICLE_PORT"
echo "********************************************************"
while ! `nc -z  vehicle-service $VEHIICLE_PORT`; do sleep 3; done
echo "*******  vehicle service has started"
echo "********************************************************"


echo "Starting vehicle-simulator-service "
echo "********************************************************"
java -Xmx400m   -Djava.security.egd=file:/dev/./urandom -Djasypt.encryptor.password=altenpass@admin  -Dspring.profiles.active=stage -jar  /usr/app/vehicle-simulator.jar
