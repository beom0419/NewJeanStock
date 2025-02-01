package com.sascom.notificationbackend.error;

import com.sascom.notificationbackend.error.code.BasicErrorCode;
import com.sascom.notificationbackend.error.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;

public abstract class BaseExceptionHandler {
    protected ResponseEntity<ErrorResponse> createErrorResponse(BasicErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getStatus()).body(ErrorResponse.of(errorCode));
    }
}
