package com.doomole.portfolio.dto.response.common;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResSuccess<T> {
    private int status;
    T Data;

    public ResSuccess(T Data) {
        this.status = HttpStatus.OK.value();
        this.Data = Data;
    }
}
