[Java HttpBin Swagger](https://jibijose.github.io/swagger)
==================================
[Java HttpBin Apidocs](https://jibijose.github.io/apidocs/httpbin)
==================================

[![Build Status](https://ci.appveyor.com/api/projects/status/github/jibijose/httpbin?branch=master&svg=true)](https://ci.appveyor.com/project/jibijose/httpbin)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.jibijose/httpbin/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.jibijose/httpbin)   
[![Coverage Status](http://img.shields.io/coveralls/jibijose/httpbin/master.svg?style=flat-square)](https://coveralls.io/r/jibijose/httpbin?branch=master)
[![GitHub contributors](https://img.shields.io/github/contributors/jibijose/httpbin.svg)](https://github.com/jibijose/httpbin/graphs/contributors)[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)  
[![](https://images.microbadger.com/badges/image/jibijose/httpbin.svg)](https://microbadger.com/images/jibijose/httpbin)
[![](https://img.shields.io/github/repo-size/jibijose/httpbin)](https://microbadger.com/images/jibijose/httpbin)
[![](https://images.microbadger.com/badges/version/jibijose/httpbin.svg)](https://microbadger.com/images/jibijose/httpbin)
[![CircleCI](https://dl.circleci.com/status-badge/img/gh/jibijose/httpbin/tree/master.svg?style=svg)](https://dl.circleci.com/status-badge/redirect/gh/jibijose/httpbin/tree/master)
[![DeepSource](https://app.deepsource.com/gh/jibijose/httpbin.svg/?label=active+issues&show_trend=true&token=HRlJTew-PUsNzGUPIGoQBCq_)](https://app.deepsource.com/gh/jibijose/httpbin/?ref=repository-badge)


.\mvnw nexus-staging:drop
https://dzone.com/articles/deploy-maven-central
https://stackoverflow.com/questions/28846802/how-to-manually-publish-jar-to-maven-central
https://jenkov.com/tutorials/maven/publish-to-central-maven-repository.html
https://central.sonatype.org/publish/publish-maven/#performing-a-release-deployment
https://central.sonatype.org/publish/publish-maven/#releasing-to-central

    
### Requirements

* Java 11+
* Maven 3.0.0 or newer.
* docker optional

### Run locally
./scripts/dockerRun.sh   

### Build locally
./scripts/dockerBuild.sh   

### Cleanup
./scripts/ckeanup.sh  

*************
### TODOs  
upload to maven central  
junit run parallel  
add external curl checkpoints  
https://assertible.com/blog/testing-an-api-using-swagger    
APIS. Cookies, Req/Resp Inspection.  
hold/release endpoints  
ram/cpu/disk speed api
consumer disk speed api.  
Open Api Spec 3.0   
Java api web page   
Date Apis. add delete days/weeks/months/years    
https://github.com/gregswindle/maven-code-quality-pom#437-mvn-pmdcheck   


***************   
#### OpenShift   
https://console-openshift-console.apps.us-east-1.starter.openshift-online.com/console/command-line
oc login --token=TOKEN --server=https://api.us-east-1.starter.openshift-online.com:6443
oc config view

oc get projects
oc project httpbin

oc apply -f openshift.yaml

oc get svc
oc get pods
