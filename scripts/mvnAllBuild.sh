#!/bin/bash

./mvnw com.spotify.fmt:fmt-maven-plugin:format

./mvnw clean fmt:check pmd:check pmd:cpd-check checkstyle:check package spotbugs:check site