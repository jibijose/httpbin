[Java HttpBin Api](https://jibijose.github.io/swagger)
==================================

[![Build Status](https://ci.appveyor.com/api/projects/status/github/jibijose/httpbin?branch=master&svg=true)](https://ci.appveyor.com/project/jibijose/httpbin)
[![Build Status](https://travis-ci.org/jibijose/httpbin.svg?branch=master)](https://travis-ci.org/jibijose/httpbin)  
[![Coverage Status](http://img.shields.io/coveralls/jibijose/httpbin/master.svg?style=flat-square)](https://coveralls.io/r/jibijose/httpbin?branch=master)
[![GitHub contributors](https://img.shields.io/github/contributors/jibijose/httpbin.svg)](https://github.com/jibijose/httpbin/graphs/contributors)[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)  
[![](https://images.microbadger.com/badges/image/jibijose/httpbin.svg)](https://microbadger.com/images/jibijose/httpbin)
[![](https://img.shields.io/github/repo-size/jibijose/httpbin)](https://microbadger.com/images/jibijose/httpbin)
[![](https://images.microbadger.com/badges/version/jibijose/httpbin.svg)](https://microbadger.com/images/jibijose/httpbin)

    
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
host website somewhere, add external curl checkpoints  
add gradle support  
APIS. Cookies, Req/Resp Inspection.  
hold/release endpoints  
Network response big file.  
Image/pdf/excel/doc etc responses  
System ram/cpu/disk report api
ram/cpu/disk speed api
consumer disk speed api.

add gradle support  
update jdk11 support  
XML model response.  
Swagger Model Beans  
remove assertJ assert*  
