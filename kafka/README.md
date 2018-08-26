# kafka

Crear topic (conectado en nodo zoo3)

bin/kafka-topics.sh --create --zookeeper zoo1:2181/kafka --replication-factor 3 --partitions 1 --topic my-replicated-topic

[POC Golden Gate] 

/usr/local/kafka/bin/kafka-topics.sh --create --zookeeper zoo1:2181,zoo2:2181,zoo3:2181/kafka --replication-factor 3 --partitions 2 --topic oggtopic 

/usr/local/kafka/bin/kafka-topics.sh --zookeeper zoo1:2181,zoo2:2181,zoo3:2181/kafka --describe --topic oggtopic 

/usr/local/kafka/bin/kafka-console-consumer.sh --bootstrap-server kafka1:9092 --topic oggtopic --from-beginning 

/usr/local/kafka/bin/kafka-console-producer.sh --broker-list kafka1:9092,kafka2:9092,kafka3:9092 --topic oggtopic 

 

CREATE 

/usr/local/kafka/bin/kafka-topics.sh --create --zookeeper zoo1:2181,zoo2:2181,zoo3:2181/kafka --replication-factor 3 --partitions 2 --topic test-topic 

 

DELETE TOPIC  

/usr/local/kafka/bin/kafka-topics.sh --zookeeper zoo1:2181,zoo2:2181,zoo3:2181/kafka --delete  --topic test-topic 
 

DESCRIBE 

/usr/local/kafka/bin/kafka-topics.sh --zookeeper zoo1:2181,zoo2:2181,zoo3:2181/kafka --describe --topic test-topic 
