#!/bin/bash

./mvnw clean
./mvnw fmt:check
./mvnw pmd:check pmd:cpd-check
./mvnw checkstyle:check
./mvnw package
./mvnw spotbugs:check
./mvnw site