#!/bin/bash

./mvnw clean fmt:check pmd:check pmd:cpd-check checkstyle:check package findbugs:check site