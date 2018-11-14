package aopazo_kafka.test;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;
import org.apache.log4j.*;

public class App {

	static Logger logger = Logger.getLogger("org.apache.kafka");
	
   public static void main(String[] args) throws UnknownHostException{

       Properties props = new Properties();
       props.put("client.id", InetAddress.getLocalHost().getHostName()); 
       props.put("bootstrap.servers", args[0]);

	props.put("security.protocol", "SSL");
	props.put("ssl.truststore.location","/home/absalon/client.truststore.jks");
	props.put("ssl.truststore.password", "absalon");
	props.put("ssl.keystore.location", "/home/absalon/client.keystore.jks");
	props.put("ssl.keystore.password", "absalon");
	props.put("ssl.key.password", "absalon");
       
       /**
        * If acks=all, the producer will receive a success response from the broker once all in-sync
        *  replicas received the message. This is the safest mode since you can make sure more than 
        *  one broker has the message and that the message will survive even in the case of crash 
        *  (more information on this in Chapter 5). However, the latency we discussed in the acks=1 
        *  case will be even higher, since we will be waiting for more than just one broker to receive the message.
        */
       props.put("acks", "all");
       /**
        * retries parameter will control how many times the producer will retry sending the message before giving 
        * up and notifying the client of an issue. By default, the producer will wait 100ms between retries, 
        * but you can control this using the "retry.backoff.ms" parameter. 
        */
       props.put("retries", 2);
       props.put("retry.backoff.ms", 500);
       //props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, Integer.toString(Integer.MAX_VALUE));
       //props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG,Integer.toString(Integer.MAX_VALUE)); // increase to infinity from default of 300 s

       props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
       props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");

       Producer<String, String> producer = new KafkaProducer<String, String>(props);
       TestCallback callback = new TestCallback();

       for (long i = 0; i < 100 ; i++) {
    	   long time = System.currentTimeMillis();
           ProducerRecord<String, String> data = new ProducerRecord<String, String>(
                   "test-topic", 
                   "key-" + i +"-"+time, 
                   "message-"+i 
                  );
           // ASYNC
           logger.info("Sending key: ("+"key-" + i +"-"+time+")");
           producer.send(data, callback);
           /*
            *  // SYNC
            * try {
        	    	producer.send(data).get();
	        	} catch (Exception e) {
	        	    logger.error(e);
	        	} 
            */
           
       }

       producer.close();
   }

   // if you want to invoke some code after the write has completed you can also provide a callback. 
   // In Java this is implemented as a Callback object:
   private static class TestCallback implements Callback {
       public void onCompletion(RecordMetadata recordMetadata, Exception e) {
           if (e != null) {
        	   /*
        	    * If the broker failed to write the messages, it will return an error. 
        	    * When the producer receives an error, it may retry sending the message a
        	    *  few more times before giving up and returning an error
        	    */
               logger.info("Error - while producing message to topic :" + recordMetadata,e);
           } else {
        	   /*
        	    * If the messages were successfully written to Kafka, it will return a RecordMetadata 
        	    * object with the topic, partition, and the offset of the record within the partition. 
        	    */
               String message = String.format("OK - Sent message to topic:%s partition:%s  offset:%s", 
            		   	recordMetadata.topic(), recordMetadata.partition(), recordMetadata.offset());
               logger.info(message);
           }
       }
   }

}
