package com.webculcate.eventservicecore.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import static com.webculcate.eventservicecore.constant.ServiceConstant.WEBCULCATE_TOPIC_CAPACITY_REDUCTION_ROLLBACK;

@Configuration
public class TopicConfiguration {

    @Bean
    public NewTopic buildTopicCapacityReduction() {
        return TopicBuilder.name(WEBCULCATE_TOPIC_CAPACITY_REDUCTION_ROLLBACK).build();
    }

}
