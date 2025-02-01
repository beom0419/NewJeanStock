package com.sascom.notificationbackend.email.application;

import com.sascom.notificationbackend.email.EmailProperties;
import jakarta.annotation.PostConstruct;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class JavaMailSenderInitializer {
    private final EmailProperties emailProperties;
    private JavaMailSenderImpl mailSender;

    public JavaMailSenderInitializer(EmailProperties emailProperties) {
        this.emailProperties = emailProperties;
        this.mailSender = new JavaMailSenderImpl();
    }

    @PostConstruct
    private void initialize() {
        mailSender.setHost(emailProperties.getHost());
        mailSender.setPort(emailProperties.getPort());
        mailSender.setUsername(emailProperties.getUsername());
        mailSender.setPassword(emailProperties.getPassword());

        Properties properties = mailSender.getJavaMailProperties();
        properties.put("mail.transport.protocol", emailProperties.getProtocol());
        properties.put("mail.smtp.auth", emailProperties.getSmtpAuthEnable());

        properties.put("mail.smtp.starttls.enable", emailProperties.getStarttlsEnable());
        properties.put("mail.debug", emailProperties.getDebugEnable());
    }

    public JavaMailSenderImpl getMailSender() {
        if (mailSender == null) {
            mailSender = new JavaMailSenderImpl();
            initialize();
        }
        return mailSender;
    }
}
