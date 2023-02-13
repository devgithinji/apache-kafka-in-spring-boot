package com.densoft.kafkaproject.kafka;

import com.densoft.kafkaproject.payload.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
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
