FROM openjdk:8u141-jdk-slim
LABEL maintainer="jibijose@yahoo.com"
EXPOSE 8080

RUN mvn clean package
COPY launch.sh /service/launch.sh
RUN chmod 755 /service/launch.sh
COPY target/httpbin-1.0.0.jar /service/app.jar
RUN chmod 755 /service/app.jar

ENTRYPOINT ["/service/launch.sh"]
