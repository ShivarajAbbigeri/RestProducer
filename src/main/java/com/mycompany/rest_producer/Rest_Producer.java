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

//import util.properties packages
import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

//import simple producer packages
import org.apache.kafka.clients.producer.Producer;

//import KafkaProducer packages
import org.apache.kafka.clients.producer.KafkaProducer;

//import ProducerRecord packages
import org.apache.kafka.clients.producer.ProducerRecord;

//Create java class named “SimpleProducer”
public class Rest_Producer {
   
   Rest_Producer(){
   }
   void produce(){
      //Assign topicName to string variable
      String topicName = "rest-topic";
      
      // create instance for properties to access producer configs   
      Properties props = new Properties();
      
      //Assign localhost id
      props.put("bootstrap.servers", "localhost:9092");
      
      //Set acknowledgements for producer requests.      
      props.put("acks", "all");
      
      //If the request fails, the producer can automatically retry,
      props.put("retries", 0);
      
      //Specify buffer size in config
      props.put("batch.size", 16384);
      
      //Reduce the no of requests less than 0   
      props.put("linger.ms", 1);
      
      //The buffer.memory controls the total amount of memory available to the producer for buffering.   
      props.put("buffer.memory", 33554432);
      
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");  
      props.put("value.serializer", 
         "org.apache.kafka.common.serialization.StringSerializer");
      
      Producer<String,String> producer = new KafkaProducer<String,String>(props);
       try{    
      URL url = new URL("https://jsonplaceholder.typicode.com/users");
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      conn.setRequestProperty("Accept", "application/json");
      if (conn.getResponseCode() != 200) {
          throw new RuntimeException("Failed : HTTP error code : "+ conn.getResponseCode());
      }
      Scanner sc = new Scanner(url.openStream());
String inline="";
while(sc.hasNext())

{

inline=sc.nextLine();
producer.send(new ProducerRecord<String,String>(topicName, inline));
}


sc.close();
          
      
      conn.disconnect();
      System.out.println("Message sent successfully");
      producer.close();  
     
     
    }catch(Exception e){
    System.out.println(e);
    }

   }      
}

