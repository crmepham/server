#!/bin/bash
java -jar backend-service/target/backend-service-1.0-SNAPSHOT.jar &
java -jar crawler-service/target/crawler-service-1.0-SNAPSHOT.jar &
java -jar frontend-service/target/frontend-service-1.0-SNAPSHOT.jar &
echo 'Started server...'