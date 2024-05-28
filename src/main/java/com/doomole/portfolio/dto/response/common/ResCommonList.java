package com.doomole.portfolio.dto.response.common;

import lombok.Data;

@Data
public class ResCommonList<T> {
    private long count;
    private T list;

    public ResCommonList(long count, T Data) {
        this.count = count;
        this.list = Data;
    }
}
