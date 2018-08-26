# kafka

Crear topic (conectado en nodo zoo3)

bin/kafka-topics.sh --create --zookeeper zoo1:2181/kafka --replication-factor 3 --partitions 1 --topic my-replicated-topic


/usr/local/kafka/bin/kafka-console-consumer.sh --bootstrap-server kafka1:9092 --topic test-topic --from-beginning 

/usr/local/kafka/bin/kafka-console-producer.sh --broker-list kafka1:9092,kafka2:9092,kafka3:9092 --topic oggtopic 


LIST
/usr/local/kafka/bin/kafka-console-consumer.sh --bootstrap-server kafka1:9062,kafka2:9063,kafka3:9064 --topic test-topic --from-beginning

CREATE 
/usr/local/kafka/bin/kafka-topics.sh --create --zookeeper zoo1:2181,zoo2:2181,zoo3:2181/kafka --replication-factor 3 --partitions 2 --topic test-topic 

 

DELETE TOPIC  
/usr/local/kafka/bin/kafka-topics.sh --zookeeper zoo1:2181,zoo2:2181,zoo3:2181/kafka --delete  --topic test-topic 
 

DESCRIBE 
/usr/local/kafka/bin/kafka-topics.sh --zookeeper zoo1:2181,zoo2:2181,zoo3:2181/kafka --describe --topic test-topic 


[RUN CLIENT producer]
docker run --rm --network kafka_default --name kafka-client \
--mount type=bind,source=/home/absalon/poc-kafka/kafka/kafka-consumer-producer/target,target=/home \
openjdk:8-jre-alpine \
java -cp /home/test-0.0.1-SNAPSHOT-jar-with-dependencies.jar aopazo_kafka.test.App kafka1:9092,kafka2:9092,kafka3:9092
