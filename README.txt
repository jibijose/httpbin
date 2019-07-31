git clone https://github.com/jibijose/httpbin
cd httpbin

docker login (login to hub.docker.com)
docker build -t registry.hub.docker.com/jibijose/httpbin:latest .
docker push registry.hub.docker.com/jibijose/httpbin:latest


# docker run -d -p 8080:8080 registry.hub.docker.com/jibijose/httpbin:latest
# docker stop $(docker ps -aq)
# docker rm $(docker ps -aq)