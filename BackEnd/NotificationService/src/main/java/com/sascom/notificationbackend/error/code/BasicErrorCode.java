package com.sascom.notificationbackend.error.code;

import org.springframework.http.HttpStatus;

public interface BasicErrorCode {
    HttpStatus getStatus();
    String getCode();
    String getMessage();
}
