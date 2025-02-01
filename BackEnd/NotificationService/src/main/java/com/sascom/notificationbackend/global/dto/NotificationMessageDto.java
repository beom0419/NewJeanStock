package com.sascom.notificationbackend.global.dto;

public record NotificationMessageDto(
        String receiver,
        String title,
        String body
) {
}
