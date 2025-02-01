package com.sascom.notificationbackend.global.kafka.config;

import com.sascom.notificationbackend.global.dto.NotificationMessageDto;
import com.sascom.notificationbackend.global.kafka.properties.FcmConsumerProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.util.backoff.BackOff;
import org.springframework.util.backoff.FixedBackOff;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class KafkaFcmConsumerConfig {

    private final FcmConsumerProperties fcmConsumerProperties;

    @Value(value = "${kafka.backoff.interval}")
    private Long interval;
    @Value(value = "${kafka.backoff.max_failure}")
    private Long maxAttempts;

    @Bean
    public Map<String, Object> fcmConsumerConfig() {

        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                fcmConsumerProperties.bootstrapServers());
        props.put(ConsumerConfig.GROUP_ID_CONFIG,
                fcmConsumerProperties.groupId());

        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                JsonDeserializer.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,
                fcmConsumerProperties.autoOffsetReset());

        return props;
    }

    @Bean
    public ConsumerFactory<String, NotificationMessageDto> fcmConsumerFactory() {

        StringDeserializer stringDeserializer = new StringDeserializer();
        JsonDeserializer<NotificationMessageDto> jsonDeserializer =
                new JsonDeserializer(NotificationMessageDto.class, false);

        return new DefaultKafkaConsumerFactory<>(fcmConsumerConfig(), stringDeserializer, jsonDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, NotificationMessageDto> fcmKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String,NotificationMessageDto> kafkaListenerContainerFactory
                = new ConcurrentKafkaListenerContainerFactory<>();
        kafkaListenerContainerFactory.setConsumerFactory(fcmConsumerFactory());
        return kafkaListenerContainerFactory;
    }

    @Bean
    public DefaultErrorHandler fcmErrorHandler() {
        BackOff fixedBackOff = new FixedBackOff(interval, maxAttempts);
        return new DefaultErrorHandler((consumerRecord, e) ->
                log.error("consumed record: {}", consumerRecord), fixedBackOff
        );
    }
}
