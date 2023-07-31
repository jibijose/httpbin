#!/bin/bash

VERSION=2.0.0-SNAPSHOT

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