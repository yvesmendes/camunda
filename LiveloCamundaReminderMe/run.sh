#!/bin/sh

echo "********************************************************"
echo "Starting Config Client 1                           "
echo "********************************************************"
java -Djava.security.egd=file:/dev/./urandom -Dserver.port=$SERVER_PORT   \
     -Dspring.profiles.active=$PROFILE                                   \
     -jar /usr/local/application/config-client.jar
