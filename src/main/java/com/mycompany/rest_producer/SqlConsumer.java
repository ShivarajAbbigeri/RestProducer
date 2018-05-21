/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.rest_producer;

/**
 *
 * @author shivaraj
 */
import java.util.Properties;
import java.util.Arrays;
import java.util.UUID;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import org.apache.kafka.common.serialization.StringDeserializer;

public class SqlConsumer {
   public static void main(String[] args) throws Exception {
      
      //Kafka consumer configuration settings
      //String topicName = "test";
      Properties props = new Properties();
      
      props.put("bootstrap.servers", "localhost:9092");
      props.put(ConsumerConfig.GROUP_ID_CONFIG, UUID.randomUUID().toString());
      props.put(ConsumerConfig.GROUP_ID_CONFIG, "KafkaExampleAvroConsumer");

        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
        //Use Kafka Avro Deserializer.


        //Schema registry location.

      KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
      
      //Kafka Consumer subscribes list of topics here.
      consumer.subscribe(Arrays.asList("second-example-dept"));
      
      //print the topic name
      
      
      while (true) {
          
         ConsumerRecords<String, String> records = consumer.poll(100);
         
         for (ConsumerRecord<String, String> record : records)
         
         // print the offset,key and value for the consumer records.
         System.out.printf("offset = %d, key = %s, value = %s\n", 
            record.offset(), record.key(), record.value());
         
      }
   }
}
