package com.sascom.notificationbackend.email;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class EmailProperties {

    private final String host;
    private final int port;
    private final String username;
    private final String password;
    private final String protocol;
    private final String smtpAuthEnable;
    private final String starttlsEnable;
    private final String debugEnable;

    public EmailProperties(
            @Value("${spring.mail.host}") String host,
            @Value("${spring.mail.port}") int port,
            @Value("${spring.mail.username}") String username,
            @Value("${spring.mail.password}") String password,
            @Value("${spring.mail.protocol}") String protocol,
            @Value("${spring.mail.properties.mail.smtp.auth}") String smtpAuthEnable,
            @Value("${spring.mail.properties.mail.smtp.starttls.enable}") String starttlsEnable,
            @Value("${spring.mail.properties.mail.debug}") String debugEnable) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.protocol = protocol;
        this.smtpAuthEnable = smtpAuthEnable;
        this.starttlsEnable = starttlsEnable;
        this.debugEnable = debugEnable;
    }
}