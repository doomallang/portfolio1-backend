package com.doomole.portfolio.controller;

import com.doomole.portfolio.dto.response.common.ResSuccess;
import com.doomole.portfolio.dto.response.finance.ResNews;
import com.doomole.portfolio.dto.response.finance.ResNowStockPrice;
import com.doomole.portfolio.dto.response.finance.ResStock;
import com.doomole.portfolio.service.FinanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/finance")
public class FinanceController {
    private final FinanceService financeService;

    @GetMapping("/stock/batch")
    public ResSuccess<String> doStockBatch() {
        financeService.doStockBatch();

        return new ResSuccess<>("success");
    }

    @GetMapping("/stock/recommend/batch")
    public ResSuccess<String> doStockRecommendBatch() {
        financeService.doStockRecommendBatch();

        return new ResSuccess<>("success");
    }

    @GetMapping("/stock/recommend")
    public ResSuccess<List<ResStock>> getRecommendedStock() {
        List<ResStock> resStockList = financeService.getRecommendedStocks();

        return new ResSuccess<>(resStockList);
    }

    @GetMapping("/stock/list")
    public ResSuccess<List<ResStock>> getStockList(@RequestParam(value="sector") String sector) {
        List<ResStock> resStockList = financeService.getStockList(sector);

        return new ResSuccess<>(resStockList);
    }

    @GetMapping("/stock")
    public ResSuccess<ResStock> getStock(@RequestParam(value="stockUid") long stockUid) {
        ResStock resStock = financeService.getStock(stockUid);

        return new ResSuccess<>(resStock);
    }

    @GetMapping("/stock/recommend/list")
    public ResSuccess<List<ResStock>> getStockRecommendList() {
        List<ResStock> resStockList = financeService.getStockRecommendList();

        return new ResSuccess<>(resStockList);
    }

    @GetMapping("/stock/sector/list")
    public ResSuccess<List<String>> getStockSectorList() {
        List<String> sectorList = financeService.getStockSectorList();

        return new ResSuccess<>(sectorList);
    }

    @GetMapping("/stock/price")
    public ResSuccess<ResNowStockPrice> getNowStockPrice(@RequestParam(value="stockCode") String stockCode) {
        ResNowStockPrice resNowStockPrice = financeService.getNowStockPrice(stockCode);

        return new ResSuccess<>(resNowStockPrice);
    }

    @GetMapping("/stock/news")
    public ResSuccess<List<ResNews>> getNewsList(@RequestParam(value="stockName") String stockName) {
        List<ResNews> resNewsList = financeService.getNewsList(stockName);

        return new ResSuccess<>(resNewsList);
    }
}
