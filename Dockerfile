FROM openjdk:8u242-jdk-slim AS builder
LABEL maintainer=jibijose@yahoo.com
EXPOSE 8080

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


FROM openjdk:8u242-jdk-slim as package
COPY --from=builder /tmp/app/target/httpbin-*.*.*.jar /service/app.jar

RUN apt-get update -qq && \
    apt-get install sudo -y -qq && \
    groupadd -g 1000 appgroup && \
    useradd -r -s /bin/bash -u 1000 -g appgroup -m appuser -p "$(openssl passwd -1 appuser)" && \
    usermod -aG sudo appuser

USER appuser
ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-XX:MaxRAMFraction=1",  "-XX:+UseG1GC", "-XX:GCTimeRatio=19", "-XX:MinHeapFreeRatio=10", "-XX:MaxHeapFreeRatio=10", "-jar", "/service/app.jar"]
