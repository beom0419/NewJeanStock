package com.sascom.notificationbackend.error;

import com.sascom.notificationbackend.error.exception.TokenNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class NotificationExceptionHandler extends BaseExceptionHandler {
    @ExceptionHandler(TokenNotFoundException.class)
    protected ResponseEntity<?> handleAccountNotFoundException(TokenNotFoundException e) {
        log.error(e.getMessage());
        return createErrorResponse(e.getErrorCode());
    }
}
