package com.sascom.notificationbackend.global.kafka.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "kafka.fcm-consumer")
public record FcmConsumerProperties(
        String bootstrapServers,
        String defaultTopic,
        String groupId,
        String autoOffsetReset,
        String keyDeserializer,
        String valueDeserializer) {
}
