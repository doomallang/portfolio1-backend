package com.doomole.portfolio.dto.response.common;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

@Data
public class ResError {
    private Long timestamp;
    private int status;
    private int code;
    private String error;
    private String exception;
    private String message;
    private String errorMessage;
    private String path;

    public static ResError create(Long timestamp, int status, String error, String exception, String message, String path, boolean isFailException) {
        JsonNode jsonNode = null;
        int code = 0;
        message = message == null ? "" : message;
        ResError dto = new ResError();
        dto.setTimestamp(timestamp);
        dto.setStatus(status);
        dto.setError(error);
        dto.setException(exception);
        dto.setCode(code);
        dto.setMessage(status == 500 ? "" : message);
        dto.setErrorMessage(message); // 다국어 메세지 처리 [ agent 사용]
        dto.setPath(path);

        return dto;
    }
}
