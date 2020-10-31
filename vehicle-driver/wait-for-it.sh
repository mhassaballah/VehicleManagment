#!/bin/sh
echo "********************************************************"
echo "Waiting for the gateway service to start on port $GATEWAY_PORT"
echo "********************************************************"
while ! `nc -z  gateway-service $GATEWAY_PORT`; do sleep 3; done
echo "*******  gateway service has started"

echo "********************************************************"
echo "Starting Starting vehicle driver app "
echo "********************************************************"

cd /usr/share/nginx/html/vehicle-driver
nginx  -c /etc/nginx/nginx.conf -g 'daemon off;'