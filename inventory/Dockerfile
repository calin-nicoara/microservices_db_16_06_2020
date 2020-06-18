FROM openjdk:11-jre
VOLUME /tmp
ADD target/inventory.jar app2.jar
RUN bash -c 'touch /app2.jar'
ENTRYPOINT exec java -server -Djava.security.egd=file:/dev/./urandom -jar /app2.jar
