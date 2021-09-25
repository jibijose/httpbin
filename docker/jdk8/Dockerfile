FROM openjdk:8u302-jdk-slim AS builder

ARG MVN_VERSION=3.8.2

RUN apt-get update -qq && \
    apt-get install wget -y -qq > /dev/null && \
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

FROM openjdk:8u302-jdk-slim AS packager
LABEL maintainer=jibijose@yahoo.com
EXPOSE 8080

COPY --from=builder /tmp/app.jar /service/app.jar

ENV JVM_OPTS "-XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -XX:MaxRAMFraction=1"
ENV LOG_OPTS ""

RUN groupadd -g 1000 appgroup && \
    useradd -r -s /bin/bash -u 1000 -g appgroup -m appuser -p "$(openssl passwd -1 appuser)" && \
    usermod -aG sudo appuser

USER appuser
ENTRYPOINT java ${JVM_OPTS} ${LOG_OPTS} -jar /service/app.jar