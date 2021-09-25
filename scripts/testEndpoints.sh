#!/bin/bash

HOST=localhost
PORT=8080

docker stop $(docker ps -aq -f ancestor=jibijose/httpbin:latest)
docker rm $(docker ps -aq -f ancestor=jibijose/httpbin:latest)

docker run -p 8080:8080 jibijose/httpbin:latest

curl -i http://localhost:8080/cpu/all/50/10