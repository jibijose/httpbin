git clone https://github.com/jibijose/httpbin
cd httpbin
docker build -t hub.docker.com/httpbin:latest .
docker push hub.docker.com/httpbin:latest


# docker run -d -p 8080:8080 hub.docker.com/httpbin:latest
# docker stop $(docker ps -aq)
# docker rm $(docker ps -aq)