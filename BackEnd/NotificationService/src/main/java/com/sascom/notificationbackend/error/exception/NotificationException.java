package com.sascom.notificationbackend.error.exception;

import com.sascom.notificationbackend.error.code.NotificationErrorCode;
import lombok.Getter;

@Getter
public class NotificationException extends RuntimeException{
    private final NotificationErrorCode errorCode;

    protected NotificationException(NotificationErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    protected static NotificationException of(NotificationErrorCode errorCode) {
        return new NotificationException(errorCode);
    }
}

