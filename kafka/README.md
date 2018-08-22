# kafka

Crear topic (conectado en nodo zoo3)

bin/kafka-topics.sh --create --zookeeper zoo1:2181/kafka --replication-factor 3 --partitions 1 --topic my-replicated-topic

test
