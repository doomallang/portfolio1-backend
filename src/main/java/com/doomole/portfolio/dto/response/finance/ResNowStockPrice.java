package com.doomole.portfolio.dto.response.finance;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResNowStockPrice {
    private String stockCode;
    private String price;
    private String diffPrice;
    private String diffRate;
}
