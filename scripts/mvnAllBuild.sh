#!/bin/bash

./mvnw clean fmt:check pmd:check pmd:cpd-check checkstyle:check package spotbugs:check site