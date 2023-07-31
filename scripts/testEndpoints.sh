#!/bin/bash

HOST=localhost
PORT=8080

./mvnw clean package -DskipTests
./mvnw clean package

./scripts/mvnAllSteps.sh
./scripts/gradleAllSteps.sh
java -jar target/httpbin-2.0.0-SNAPSHOT.jar

docker stop $(docker ps -aq -f ancestor=jibijose/httpbin:latest)
docker rm $(docker ps -aq -f ancestor=jibijose/httpbin:latest)

docker  rmi jibijose/httpbin:latest
docker build --build-arg MVN_VERSION=3.9.3 -t jibijose/httpbin:latest -f docker/jre11/Dockerfile .
docker run -p 8080:8080 jibijose/httpbin:latest

curl -i http://localhost:8080/health
curl -i http://localhost:8080/cpu/all/50/10
