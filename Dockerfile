FROM openjdk:8u242-jdk-slim
LABEL maintainer=jibijose@yahoo.com
EXPOSE 8080

RUN apt-get update -qq

RUN apt-get install wget -y -qq
RUN wget https://downloads.apache.org/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz --quiet -O /opt/apache-maven-3.6.3-bin.tar.gz
RUN tar -xvzf /opt/apache-maven-3.6.3-bin.tar.gz -C /opt
RUN rm -rf /opt/apache-maven-3.6.3-bin.tar.gz
RUN mv /opt/apache-maven-3.6.3 /opt/maven

RUN apt-get install sudo -y -qq
RUN groupadd -g 999 appgroup
RUN useradd -r -s /bin/bash -u 999 -g appgroup -m appuser -p ''
RUN usermod -aG sudo appuser

COPY src /tmp/app/src/
COPY templates /tmp/app/templates/
COPY pom.xml /tmp/app/
RUN /opt/maven/bin/mvn -f /tmp/app/pom.xml clean package -B -Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn

RUN mkdir /service
RUN chmod 755 /service
RUN cp /tmp/app/target/httpbin-*.*.*.jar /service/app.jar
RUN chmod 755 /service/app.jar

RUN rm -rf /tmp/app
RUN rm -rf ~/.m2
RUN rm -rf /opt/maven

USER appuser
ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-XX:MaxRAMFraction=1",  "-XX:+UseG1GC", "-XX:GCTimeRatio=19", "-XX:MinHeapFreeRatio=10", "-XX:MaxHeapFreeRatio=10", "-jar", "/service/app.jar"]
