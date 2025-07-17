package com.doomole.portfolio.dto.response.finance;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ResStockPrice {
    private long stockPriceUid;
    private String code;
    private LocalDate date;
    private int open;
    private int high;
    private int low;
    private Integer close;
    private long volume;
    private Double ma5;
    private Double ma10;
    private Double ma20;
    private Double upper;
    private Double lower;
    private Double ret;
    private Boolean goldenCross;
    private Boolean isTrendingUp;
    private Double macd;
    private Double signal;
    private Double histogram;
    private Integer score;
    private Double rsi;
    private Long foreignBuyVolume;
}
