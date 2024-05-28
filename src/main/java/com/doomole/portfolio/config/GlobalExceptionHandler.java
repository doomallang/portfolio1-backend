package com.doomole.portfolio.config;

import com.doomole.portfolio.dto.response.common.ResError;
import com.doomole.portfolio.exception.AuthException;
import com.doomole.portfolio.exception.BaseException;
import com.doomole.portfolio.exception.FailException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.Timestamp;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({FailException.class})
    public ResponseEntity<ResError> handleExceptionInternal(Exception e, HttpServletRequest req) {
        boolean isFailException = e.getClass().equals(FailException.class) ? true : false;
        BaseException baseException = (BaseException)e;
        HttpStatus status = baseException.getHttpStatus();
        HttpHeaders headers = new HttpHeaders();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        ResError dto = ResError.create(
                timestamp.getTime(), status.value(), status.getReasonPhrase(), status.getReasonPhrase(),
                baseException.getMessage(), req.getRequestURI(), isFailException
        );

        return new ResponseEntity<>(dto, headers, status);
    }

    @ExceptionHandler({AuthException.class})
    public ResponseEntity<ResError> handleExceptionAuth(Exception e, HttpServletRequest req) {
        boolean isAuthException = e.getClass().equals(AuthException.class) ? true : false;
        BaseException baseException = (BaseException)e;
        HttpStatus status = baseException.getHttpStatus();
        HttpHeaders headers = new HttpHeaders();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        ResError dto = ResError.create(
                timestamp.getTime(), status.value(), status.getReasonPhrase(), status.getReasonPhrase(),
                baseException.getMessage(), req.getRequestURI(), isAuthException
        );

        return new ResponseEntity<>(dto, headers, status);
    }
}
