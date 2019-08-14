FROM openjdk:8u141-jdk-slim
LABEL maintainer="jibijose@yahoo.com"
EXPOSE 8080

RUN apt-get update -qq
RUN apt-get install maven -y -qq

COPY src /tmp/app/src/
COPY pom.xml /tmp/app/
RUN mvn -f /tmp/app/pom.xml clean package -B -Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn
COPY launch.sh /tmp/app/

RUN mkdir /service
RUN cp /tmp/app/launch.sh /service
RUN chmod 755 /service/launch.sh
RUN cp /tmp/app/target/httpbin-*.*.*.jar /service/app.jar

RUN rm -rf /tmp/app
RUN rm -rf ~/.m2
RUN apt-get remove maven -y -qq

ENTRYPOINT ["/service/launch.sh"]
