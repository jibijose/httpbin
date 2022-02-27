#!/bin/bash

VERSION=1.2.2

echo "*****************************************************************************************"
echo "Tagging git repository"
git tag -a v${VERSION} -m "Version ${VERSION}"
git push origin v${VERSION}


echo "*****************************************************************************************"
echo "Tagging docker repository"
docker login registry.hub.docker.com

echo "*****************************************************************************************"
echo "Building docker repository"
docker build --build-arg MVN_VERSION=3.8.4 -t jibijose/httpbin:version-jre11-${VERSION} -f docker/jre11/Dockerfile .
docker build --build-arg MVN_VERSION=3.8.4 -t jibijose/httpbin:version-jdk11-${VERSION} -f docker/jdk11/Dockerfile .

echo "*****************************************************************************************"
echo "Pushing docker repository"
docker push jibijose/httpbin:version-jre11-${VERSION}
docker push jibijose/httpbin:version-jdk11-${VERSION}

