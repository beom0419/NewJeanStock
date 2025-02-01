package com.sascom.notificationbackend.email.application;

import com.sascom.notificationbackend.email.EmailProperties;
import com.sascom.notificationbackend.global.application.MessageSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmailSender implements MessageSender {

    private final JavaMailSenderInitializer javaMailSenderInitializer;
    private final JavaMailSenderImpl mailSender;

    public EmailSender(JavaMailSenderInitializer javaMailSenderInitializer) {
        this.javaMailSenderInitializer = javaMailSenderInitializer;
        mailSender = this.javaMailSenderInitializer.getMailSender();
    }

    @Override
    public void sendMessage(String receiverAddress, String title, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@chickenstock.com");
        message.setTo(receiverAddress);
        message.setSubject(title);
        message.setText(body);

        mailSender.send(message);
    }
}
