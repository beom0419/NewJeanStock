package com.sascom.notificationbackend.email.controller;

import com.sascom.notificationbackend.email.application.EmailSender;
import com.sascom.notificationbackend.global.dto.NotificationMessageDto;
import com.sascom.notificationbackend.global.kafka.producer.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailSender emailSender;
    private final KafkaProducer kafkaProducer;

    @PostMapping
    public void sendEmail(@RequestBody NotificationMessageDto messageDto) {
        kafkaProducer.sendEmailMessageToKafka(messageDto);
//        emailSender.sendMessage(messageDto.receiver(), messageDto.title(), messageDto.body());
    }

}
