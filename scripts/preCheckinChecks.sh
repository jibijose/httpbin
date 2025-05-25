#!/bin/bash

./mvnw wrapper:wrapper
./mvnw versions:display-property-updates
./mvnw versions:display-plugin-updates
./mvnw versions:display-dependency-updates
./mvnw versions:use-latest-versions
###./mvnw release:update-versions
#################################################################################
./gradlew wrapper
./gradlew dependencyUpdates
#################################################################################
#./mvnw com.spotify.fmt:fmt-maven-plugin:format
./mvnw clean fmt:check pmd:check pmd:cpd-check checkstyle:check package spotbugs:check site
#################################################################################
./gradlew clean build
#./gradlew clean fmt:check pmdMain checkstyle:check build findBugsMain site


./gradlew clean spotlessCheck check build