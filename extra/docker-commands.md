## Basic Commands

docker run hello-world

docker pull busybox

docker images

docker run busybox

docker run busybox echo "hello from busybox"

docker ps

docker ps -a

docker run -it busybox sh

docker rm

docker rm $(docker ps -a -q -f status=exited)

docker container prune

docker rmi


- Images - The blueprints of our application which form the basis of containers. 
In the demo above, we used the docker pull command to download the busybox image.
- Containers - Created from Docker images and run the actual application. 
We create a container using docker run which we did using the busybox 
image that we downloaded. A list of running containers can be seen using the docker ps command.
- Docker Daemon - The background service running on the host 
that manages building, running and distributing Docker containers. 
The daemon is the process that runs in the operating system which clients talk to.
- Docker Client - The command line tool that allows the user
 to interact with the daemon. More generally, there can be other forms of clients too - such as Kitematic which provide a GUI to the users.
- Docker Hub - A registry of Docker images. You can
 think of the registry as a directory of all available Docker images. If required, one can host their own Docker registries and can use them for pulling images.


## WEb apps


docker run --rm prakhar1989/static-site

docker run -d -P --name static-site prakhar1989/static-site
docker port static-site

## Docker images

An important distinction to be aware of when it comes to images is the difference between base and child images.

- Base images are images that have no parent image, usually images with an OS like ubuntu, busybox or debian.
- Child images are images that build on base images and add additional functionality.

Then there are official and user images, which can be both base and child images.

- Official images are images that are officially maintained and supported by the folks at Docker. These are typically one word long. In the list of images above, the python, ubuntu, busybox and hello-world images are official images.
- User images are images created and shared by users like you and me. They build on base images and add additional functionality. Typically, these are formatted as user/image-name.


## Dockerfile

- FROM
- WORKDIR
- COPY
- RUN 
- EXPOSE
- CMD

```
docker build
```

Docker push

## Docker network

docker network ls

docker network inspect bridge

docker network create


## Docker-compose

Params:
- services
- image
- container_name
- environment
- ports
- volumes
- command

docker-compose up

docker-compose down -v











