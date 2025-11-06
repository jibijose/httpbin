#!/bin/bash

./mvnw clean package spotbugs:check fmt:check checkstyle:check pmd:check site
docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)
#docker rmi $(docker images -a -q)
docker  rmi jibijose/httpbin:jdk17-latest

#docker build --build-arg MVN_VERSION=3.9.11 -t jibijose/httpbin:jdk17-latest -f docker/jdk17/Dockerfile .
#docker build --build-arg MVN_VERSION=3.9.11 -t jibijose/httpbin:jdk21-latest -f docker/jdk21/Dockerfile .
#docker build --build-arg MVN_VERSION=3.9.11 -t jibijose/httpbin:jdk25-latest -f docker/jdk25/Dockerfile .