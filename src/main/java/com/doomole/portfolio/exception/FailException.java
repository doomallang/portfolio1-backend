package com.doomole.portfolio.exception;

import org.springframework.http.HttpStatus;

public class FailException extends BaseException {
    public FailException(final String message) {
        super(message);
    }

    public FailException(final String message, final int code) {
        super(message, code);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.UNPROCESSABLE_ENTITY;
    }
}
