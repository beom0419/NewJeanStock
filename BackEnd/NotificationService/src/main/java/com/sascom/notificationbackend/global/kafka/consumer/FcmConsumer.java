package com.sascom.notificationbackend.global.kafka.consumer;

import com.sascom.notificationbackend.firebase.application.FcmSender;
import com.sascom.notificationbackend.global.dto.NotificationMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FcmConsumer {

    private final FcmSender fcmSender;

    @KafkaListener(
            topics = "${kafka.fcm-consumer.default-topic}",
            groupId = "${kafka.fcm-consumer.group-id}",
            containerFactory = "fcmKafkaListenerContainerFactory",
            concurrency = "3"
    )
    public void consumeFirebaseMessage(ConsumerRecord<String, NotificationMessageDto> messageDto) {
        long startTime = System.nanoTime();

        log.info("Success to reach Sender, Topic = FCM");
        NotificationMessageDto nmd = messageDto.value();

        fcmSender.sendMessage(nmd.receiver(), nmd.title(), nmd.body());
        log.info("Consumed Received FCM : {}}", nmd);

        // 종료 시간 기록
        long endTime = System.nanoTime();

        // 실행 시간 계산 (나노초 단위)
        long duration = endTime - startTime;

        // 실행 시간 출력 (초 단위로 변환)
        double executionTimeSec = (double) duration / 1000000000;
        System.out.printf("Execution time: %.6f seconds%n", executionTimeSec);
    }
}