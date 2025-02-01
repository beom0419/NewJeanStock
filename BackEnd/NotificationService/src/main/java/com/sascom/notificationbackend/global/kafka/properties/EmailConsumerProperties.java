package com.sascom.notificationbackend.global.kafka.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "kafka.email-consumer")
public record EmailConsumerProperties(
        String bootstrapServers,
        String defaultTopic,
        String groupId,
        String autoOffsetReset,
        String keyDeserializer,
        String valueDeserializer) {
}
