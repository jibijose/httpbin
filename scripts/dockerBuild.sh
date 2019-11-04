#!/bin/bash

mvn clean package
docker  rmi jibijose/httpbin:latest
docker build -t jibijose/httpbin:latest .