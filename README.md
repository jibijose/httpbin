[Java HttpBin](https://jibijose.github.io/swagger)
==================================

[![Build Status](https://ci.appveyor.com/api/projects/status/github/jibijose/httpbin?branch=master&svg=true)](https://ci.appveyor.com/project/jibijose/httpbin)
[![Build Status](https://travis-ci.org/jibijose/httpbin.svg?branch=master)](https://travis-ci.org/jibijose/httpbin)
[![Coverage Status](http://img.shields.io/coveralls/jibijose/httpbin/master.svg?style=flat-square)](https://coveralls.io/r/jibijose/httpbin?branch=master)
[![GitHub contributors](https://img.shields.io/github/contributors/jibijose/httpbin.svg)](https://github.com/jibijose/httpbin/graphs/contributors)[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

### Requirements

* Java 8.x
* Maven 3.0.0 or newer.

### Run locally
docker run -p 8080:8080 jibijose/httpbin  
docker run -d -p 8080:8080 registry.hub.docker.com/jibijose/httpbin:latest

### Build locally
git clone https://github.com/jibijose/httpbin  
cd httpbin  
mvn clean package  
docker build -t jibijose/httpbin:latest .  

### Cleanup
docker stop $(docker ps -aq)  
docker rm $(docker ps -aq)  
docker rmi jibijose/httpin:latest  
docker rmi registry.hub.docker.com/jibijose/httpbin:latest  
rm -rf httpbin

*************
TODOs
upload to maven central
junit run parallel
host website somewhere
add gradle support  
update jdk11 support  