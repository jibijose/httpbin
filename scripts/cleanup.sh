#!/bin/bash

docker stop $(docker ps -aq)
docker rm $(docker ps -aq)
docker rmi jibijose/httpin:latest
docker rmi registry.hub.docker.com/jibijose/httpbin:latest

mvn clean