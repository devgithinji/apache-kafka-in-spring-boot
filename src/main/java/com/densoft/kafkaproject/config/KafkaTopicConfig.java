package com.densoft.kafkaproject.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

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
