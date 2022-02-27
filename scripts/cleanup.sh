#!/bin/bash

VERSION=1.2.2

docker stop $(docker ps -aq)
docker rm $(docker ps -aq)

docker rmi jibijose/httpin:latest
docker rmi jibijose/httpbin:latest

docker rmi jibijose/httpbin:jre11-latest
docker rmi jibijose/httpbin:jdk11-latest

docker rmi jibijose/httpbin:jre11-${VERSION}
docker rmi jibijose/httpbin:jdk11-${VERSION}

./mvnw clean
./gradlew clean