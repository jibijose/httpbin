FROM openjdk:8u242-jdk-slim AS builder

ARG MVN_VERSION=3.6.3

RUN apt-get update -qq && \
    apt-get install wget -y -qq && \
    wget https://downloads.apache.org/maven/maven-3/${MVN_VERSION}/binaries/apache-maven-${MVN_VERSION}-bin.tar.gz --quiet -O /opt/apache-maven-${MVN_VERSION}-bin.tar.gz && \
    tar -xzf /opt/apache-maven-${MVN_VERSION}-bin.tar.gz -C /opt && \
    mv /opt/apache-maven-${MVN_VERSION} /opt/maven

COPY src /tmp/app/src/
COPY templates /tmp/app/templates/
COPY pom.xml /tmp/app/

RUN /opt/maven/bin/mvn -f /tmp/app/pom.xml clean package -DskipTests -B -Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn

RUN rm -rf /tmp/app/target/httpbin-*.*.*-sources.jar && \
    rm -rf /tmp/app/target/httpbin-*.*.*-javadoc.jar && \
    cp /tmp/app/target/httpbin-*.*.*.jar /tmp/app.jar

FROM openjdk:8u242-jdk-slim AS packager
LABEL maintainer=jibijose@yahoo.com
EXPOSE 8080

COPY --from=builder /tmp/app.jar /service/app.jar

ENV MAXRAMFRACTION=1
ENV GCTIMERATIO=2
ENV COMMAND "java -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -XX:MaxRAMFraction=1 -XX:+UseG1GC -XX:MaxGCPauseMillis=10000 -XX:MaxGCMinorPauseMillis=100 -XX:GCTimeRatio=2 -jar /service/app.jar"

#RUN apt-get update -qq && \
#    apt-get install sudo -y -qq > /dev/null && \
#    apt-get install curl -y -qq > /dev/null && \
#    apt-get install procps -y -qq > /dev/null && \
RUN groupadd -g 1000 appgroup && \
    useradd -r -s /bin/bash -u 1000 -g appgroup -m appuser -p "$(openssl passwd -1 appuser)" && \
    usermod -aG sudo appuser

USER appuser
#ENTRYPOINT java -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap "-XX:MaxRAMFraction=${MAXRAMFRACTION}" -XX:+UseG1GC "-XX:GCTimeRatio=${GCTIMERATIO}" -XX:MinHeapFreeRatio=10 -XX:MaxHeapFreeRatio=30 -jar /service/app.jar
#ENTRYPOINT java -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap "-XX:MaxRAMFraction=${MAXRAMFRACTION}" -XX:+UseG1GC -XX:MaxGCPauseMillis=10000 -XX:MaxGCMinorPauseMillis=100 "-XX:GCTimeRatio=${GCTIMERATIO}" -jar /service/app.jar
ENTRYPOINT ${COMMAND}