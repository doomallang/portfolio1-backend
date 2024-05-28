package com.doomole.portfolio.dto.response.common;

import lombok.Data;

@Data
public class ResAuth<T> {
    private int code;
//    private AuthData data;
    private String message;
    @Data
    private class AuthData {
        private int code;
        private String message;
    }

    public ResAuth(int code, String message) {
        this.code = code;

        this.message = message;
    }
}
