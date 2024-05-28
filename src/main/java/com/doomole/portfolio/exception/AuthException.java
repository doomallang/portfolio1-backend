package com.doomole.portfolio.exception;

import org.springframework.http.HttpStatus;

public class AuthException extends BaseException {
    public AuthException(final String message) {
        super(message);
    }

    public AuthException(final String message, final int code) {
        super(message, code);
    }

    @Override
    public HttpStatus getHttpStatus() { return HttpStatus.UNAUTHORIZED; }
}
