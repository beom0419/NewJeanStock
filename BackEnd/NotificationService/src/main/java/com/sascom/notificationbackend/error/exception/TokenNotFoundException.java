package com.sascom.notificationbackend.error.exception;

import com.sascom.notificationbackend.error.code.NotificationErrorCode;

public class TokenNotFoundException extends NotificationException{
    private TokenNotFoundException(NotificationErrorCode errorCode) {
        super(errorCode);
    }

    public static TokenNotFoundException of (NotificationErrorCode errorCode) {
        return new TokenNotFoundException(errorCode);
    }
}