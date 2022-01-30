#!/bin/bash

./mvnw clean javadoc:javadoc

cp -rf target/site/apidocs/* $1/apidocs/httpbin

cd $1
git push origin master

echo "apidocs pubished"