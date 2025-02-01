package com.sascom.notificationbackend.error.code;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum NotificationErrorCode implements BasicErrorCode {
    ERROR(HttpStatus.BAD_REQUEST, "001", "알수없는 에러입니다. 담당자에게 문의해주세요."),
    RUNTIME_ERROR(HttpStatus.BAD_REQUEST, "002", "알수없는 에러입니다. 담당자에게 문의해주세요."),
    TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, "003", "해당 memberId를 통해서 FCM 토큰을 찾을 수 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

    NotificationErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = "GLOBAL"+code;
        this.message = message;
    }

}
