#!/bin/bash

./mvnw clean package spotbugs:check fmt:check checkstyle:check pmd:check site
docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)
#docker rmi $(docker images -a -q)
docker  rmi jibijose/httpbin:jdk17-latest

#docker build --build-arg MVN_VERSION=3.9.6 -t jibijose/httpbin:jdk17-latest -f docker/jdk17/Dockerfile .