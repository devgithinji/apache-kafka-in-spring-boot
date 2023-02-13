# Apache Kafka Message Broker Usage

Producer and consumer config

```
#kafka consumer
spring.kafka.consumer.bootstrap-servers=localhost:9092
spring.kafka.consumer.group-id=myGroup
spring.kafka.consumer.auto-offset-reset=earliest
spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.consumer.value-deserializer=org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.consumer.properties.spring.json.trusted.packages=*
#kafka producer
spring.kafka.producer.bootstrap-servers=localhost:9092
spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.value-serializer=org.apache.kafka.common.serialization.StringSerializer
```

Topic Config

```
@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic densoftTopic() {
        return TopicBuilder.name("densoft").build();
    }

    @Bean
    public NewTopic densoftJsonTopic() {
        return TopicBuilder.name("densoft_json").build();
    }
}
```

Producer

```
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        log.info("Message sent {}", message);
        kafkaTemplate.send("densoft", message);
    }
}
```

```
public class JsonKafkaProducer {
    private final KafkaTemplate<String, User> kafkaTemplate;

    public void sendMessage(User user) {
        log.info("Message sent {}", user);

        Message<User> message = MessageBuilder
                .withPayload(user)
                .setHeader(KafkaHeaders.TOPIC, "densoft_json")
                .build();
        kafkaTemplate.send(message);
    }
}
```

Consumer

```
public class KafkaConsumer {
    @KafkaListener(topics = "densoft", groupId = "myGroup")
    public void consume(String message) {
        log.info("Message received {}", message);
    }


    @KafkaListener(topics = "densoft_json", groupId = "myGroup")
    public void consumeJson(User user) {
        log.info("Message received {}", user);
    }
}

```

Usage of JsonSerializer

```
#kafka consumer
spring.kafka.consumer.value-deserializer=org.springframework.kafka.support.serializer.JsonDeserializer

#kafka producer
spring.kafka.producer.value-serializer=org.springframework.kafka.support.serializer.JsonSerializer
```
