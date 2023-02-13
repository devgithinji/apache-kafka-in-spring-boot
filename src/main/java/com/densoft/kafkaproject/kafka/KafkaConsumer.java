package com.densoft.kafkaproject.kafka;

import com.densoft.kafkaproject.payload.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
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
