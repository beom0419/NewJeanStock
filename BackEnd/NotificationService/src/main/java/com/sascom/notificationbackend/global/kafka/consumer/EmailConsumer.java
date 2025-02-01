package com.sascom.notificationbackend.global.kafka.consumer;

import com.sascom.notificationbackend.email.application.EmailSender;
import com.sascom.notificationbackend.global.dto.NotificationMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailConsumer {

    private final EmailSender emailSender;

    @KafkaListener(
            topics = "${kafka.email-consumer.default-topic}",
            groupId = "${kafka.email-consumer.group-id}",
            containerFactory = "emailKafkaListenerContainerFactory",
            concurrency = "3"
    )
    public void consumeEmailMessage(ConsumerRecord<String, NotificationMessageDto> messageDto) {
        long startTime = System.nanoTime();

        log.info("Success to reach Sender, Topic = Email");
        NotificationMessageDto nmd = messageDto.value();
        emailSender.sendMessage(nmd.receiver(), nmd.title(), nmd.body());
        log.info("Consumed Received Email Message : {}}", nmd);

        // 종료 시간 기록
        long endTime = System.nanoTime();

        // 실행 시간 계산 (나노초 단위)
        long duration = endTime - startTime;

        // 실행 시간 출력 (초 단위로 변환)
        double executionTimeSec = (double) duration / 1000000000;
        log.info("Execution time: {} seconds", executionTimeSec);
    }
}