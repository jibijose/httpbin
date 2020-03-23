#!/bin/bash

mvn clean package spotbugs:check fmt:check checkstyle:check pmd:check site
docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)
docker  rmi jibijose/httpbin:latest
docker build --build-arg MVN_VERSION=3.6.3 -t jibijose/httpbin:latest .