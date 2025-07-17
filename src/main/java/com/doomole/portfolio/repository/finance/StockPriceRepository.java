package com.doomole.portfolio.repository.finance;

import com.doomole.portfolio.entity.finance.StockPrice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockPriceRepository extends JpaRepository<StockPrice, Long> {
    List<StockPrice> findAllByCode(String code);

    List<StockPrice> findAllByCodeIn(List<String> stockCodeList);
}
