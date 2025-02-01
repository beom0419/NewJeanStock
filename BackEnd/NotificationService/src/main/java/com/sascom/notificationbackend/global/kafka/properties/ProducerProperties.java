package com.sascom.notificationbackend.global.kafka.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "kafka.producer")
public record ProducerProperties(
        String bootstrapServers,
        String keySerializer,
        String valueSerializer) {
}
