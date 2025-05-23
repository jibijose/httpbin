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
docker build --build-arg MVN_VERSION=3.9.9 -t jibijose/httpbin:latest -f docker/jdk17/Dockerfile .
docker run -p 8080:8080 jibijose/httpbin:latest

curl -D http://localhost:8080/health
curl -D http://localhost:8080/cpu/all/50/10
---------------------------------------------------
docker build --build-arg MVN_VERSION=3.9.9 -t jibijose/httpbin:jdk24-latest -f docker/jdk24/Dockerfile .
docker run -p 8080:8080 jibijose/httpbin:jdk24-latest

docker run -it jibijose/httpbin:jdk24-latest /bin/bash
