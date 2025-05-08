#!/bin/bash

VERSION=2.0.0-SNAPSHOT

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
docker build --build-arg MVN_VERSION=3.9.9 -t jibijose/httpbin:jre17-latest -f docker/jre17/Dockerfile .
docker build --build-arg MVN_VERSION=3.9.9 -t jibijose/httpbin:jdk17-latest -f docker/jdk17/Dockerfile .

docker tag jibijose/httpbin:jre17-latest jibijose/httpbin:jre17-${VERSION}
docker tag jibijose/httpbin:jdk17-latest jibijose/httpbin:jdk17-${VERSION}

echo "*****************************************************************************************"
echo "Pushing docker repository"
docker push jibijose/httpbin:jre17-${VERSION}
docker push jibijose/httpbin:jdk17-${VERSION}

docker push jibijose/httpbin:jre17-latest
docker push jibijose/httpbin:jdk17-latest