package com.doomole.portfolio.dto.response.finance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResStock {
    private long stockUid;
    private String code;
    private String name;
    private String sector;
    private Double per;
    private Double pbr;
    private Double roe;
    private Double debtRatio;
    private Double marketCap;
    private Boolean recommended;
    private Integer score;
    private Double epsGrowth;
    private Double dividendYield;

    private List<ResStockPrice> stockPrices;
}
