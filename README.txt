git clone https://github.com/jibijose/httpbin
cd httpbin
mvn clean package

docker login (login to hub.docker.com)

docker rmi jibijose/httpbin:latest
docker build -t jibijose/httpbin:latest .
docker push jibijose/httpbin:latest


docker run -d -p 8080:8080 jibijose/httpbin:latest
curl -v http://localhost:8080/cpu/80/300
curl -v http://localhost:8080/health
docker stop $(docker ps -aq)
docker rm $(docker ps -aq)
docker ps -a
docker exec -it 869fea78e0d7 /bin/bash


docker run -d -p 8080:8080 registry.hub.docker.com/jibijose/httpbin:latest