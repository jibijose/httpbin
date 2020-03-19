#!/bin/bash

mvn clean package
docker rmi jibijose/httpbin:latest
docker build --build-arg MVN_VERSION=3.6.3 -t jibijose/httpbin:latest .