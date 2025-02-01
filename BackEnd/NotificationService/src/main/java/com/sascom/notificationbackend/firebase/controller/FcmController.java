package com.sascom.notificationbackend.firebase.controller;

import com.sascom.notificationbackend.firebase.application.FcmSender;
import com.sascom.notificationbackend.global.dto.NotificationMessageDto;
import com.sascom.notificationbackend.global.kafka.producer.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/firebase")
@RequiredArgsConstructor
public class FcmController {
    private final FcmSender messageSender;
    private final KafkaProducer kafkaProducer;

    @PostMapping
    public ResponseEntity<?> messaging(@RequestBody NotificationMessageDto requestDto) {
//        messageSender.sendMessage(requestDto.receiver(), requestDto.title(), requestDto.body());
        kafkaProducer.sendAlarmMessageToKafka(requestDto);
        return ResponseEntity.ok().build();
    }
}
