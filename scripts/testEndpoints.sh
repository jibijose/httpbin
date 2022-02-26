#!/bin/bash

HOST=localhost
PORT=8080

docker stop $(docker ps -aq -f ancestor=jibijose/httpbin:latest)
docker rm $(docker ps -aq -f ancestor=jibijose/httpbin:latest)

docker run -p 8080:8080 jibijose/httpbin:latest

curl -i http://localhost:8080/cpu/all/50/10

docker login registry.hub.docker.com

docker build --build-arg MVN_VERSION=3.8.4 -t jibijose/httpbin:jre11-latest -f docker/jre11/Dockerfile .
docker build --build-arg MVN_VERSION=3.8.4 -t jibijose/httpbin:jdk11-latest -f docker/jdk11/Dockerfile .

docker push jibijose/httpbin:jre11-latest
docker push jibijose/httpbin:jdk11-latest