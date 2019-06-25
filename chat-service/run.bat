docker image build -t chat .
docker run --detach -p 7070:7070 -p 7878:7878 chat:latest

