package com.sascom.notificationbackend.firebase.application;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;

@Slf4j
@Component
@DependsOn(value = "fcmSender")
public class FirebaseInitializer {

    private final String fcmAdminJsonPath;

    public FirebaseInitializer(@Value("${firebase.admin-json-path}") String fcmAdminJsonPath) {
        this.fcmAdminJsonPath = fcmAdminJsonPath;
    }

    @PostConstruct
    private void initialize() {
        try {
            FileInputStream classPathResource = new FileInputStream(fcmAdminJsonPath);
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(classPathResource))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                log.info("initialize FirebaseApp");
                FirebaseApp.initializeApp(options);
            }
        } catch (IOException e) {
            log.error("fcm 인증 실패: {}", e.getMessage());
        }
    }
}
