package com.doomole.portfolio.converter;

import com.doomole.portfolio.dto.response.finance.ResStock;
import com.doomole.portfolio.dto.response.finance.ResStockPrice;
import com.doomole.portfolio.entity.finance.Stock;
import com.doomole.portfolio.entity.finance.StockPrice;

public class FinanceConverter {

    public static ResStock toResStock(Stock stock) {
        return ResStock.builder()
                .stockUid(stock.getStockUid())
                .code(stock.getCode())
                .name(stock.getName())
                .sector(stock.getSector())
                .per(stock.getPer())
                .pbr(stock.getPbr())
                .roe(stock.getRoe())
                .debtRatio(stock.getDebtRatio())
                .marketCap(stock.getMarketCap())
                .recommended(stock.getRecommended())
                .score(stock.getScore())
                .epsGrowth(stock.getEpsGrowth())
                .dividendYield(stock.getDividendYield())
                .build();
    }

    public static ResStockPrice toResStockPrice(StockPrice stockPrice) {
        return ResStockPrice.builder()
                .stockPriceUid(stockPrice.getStockPriceUid())
                .code(stockPrice.getCode())
                .date(stockPrice.getDate())
                .open(stockPrice.getOpen())
                .high(stockPrice.getHigh())
                .low(stockPrice.getLow())
                .close(stockPrice.getClose())
                .volume(stockPrice.getVolume())
                .ma5(stockPrice.getMa5())
                .ma10(stockPrice.getMa10())
                .ma20(stockPrice.getMa20())
                .macd(stockPrice.getMacd())
                .signal(stockPrice.getSignal())
                .histogram(stockPrice.getHistogram())
                .score(stockPrice.getScore())
                .upper(stockPrice.getUpper())
                .lower(stockPrice.getLower())
                .ret(stockPrice.getRet())
                .goldenCross(stockPrice.getGoldenCross())
                .isTrendingUp(stockPrice.getIsTrendingUp())
                .foreignBuyVolume(stockPrice.getForeignBuyVolume())
                .build();
    }
}
