package com.material.producer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import com.material.shared.enums.Topic;

@Configuration
public class KafkaConfig {
    @Bean
    public NewTopic topic1() {
        return TopicBuilder.name(Topic.TXN)
                .partitions(3)
                .replicas(1)
                .build();
    }

//    @Bean
//    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
//        return new KafkaTemplate<>(producerFactory);
//    }

}
