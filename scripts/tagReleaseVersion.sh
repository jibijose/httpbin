#!/bin/bash

VERSION=1.2.3

echo "*****************************************************************************************"
echo "force deleting existing tag"
git tag -d v${VERSION}
git push --delete origin v${VERSION}

echo "Tagging git repository"
git tag -a v${VERSION} -m "Version ${VERSION}"
git push origin v${VERSION}


echo "*****************************************************************************************"
echo "Tagging docker repository"
docker login registry.hub.docker.com

echo "*****************************************************************************************"
echo "Building docker repository"
docker build --build-arg MVN_VERSION=3.9.3 -t jibijose/httpbin:jre11-latest -f docker/jre11/Dockerfile .
docker build --build-arg MVN_VERSION=3.9.3 -t jibijose/httpbin:jdk11-latest -f docker/jdk11/Dockerfile .

docker tag jibijose/httpbin:jre11-latest jibijose/httpbin:jre11-${VERSION}
docker tag jibijose/httpbin:jdk11-latest jibijose/httpbin:jdk11-${VERSION}

echo "*****************************************************************************************"
echo "Pushing docker repository"
docker push jibijose/httpbin:jre11-${VERSION}
docker push jibijose/httpbin:jdk11-${VERSION}

docker push jibijose/httpbin:jre11-latest
docker push jibijose/httpbin:jdk11-latest