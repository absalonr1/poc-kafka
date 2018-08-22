FROM openjdk:8-jre-alpine

# Install required packages
RUN apk add --no-cache \
    bash \
    su-exec


 
# kafka
RUN wget http://www-eu.apache.org/dist/kafka/2.0.0/kafka_2.12-2.0.0.tgz; \
    tar -zxf kafka_2.12-2.0.0.tgz; \
    mv kafka_2.12-2.0.0 /usr/local/kafka; \
    mkdir /tmp/kafka-logs

ENTRYPOINT ["/usr/local/kafka/bin/kafka-server-start.sh", "/usr/local/kafka/config/server.properties"]
