package com.doomole.portfolio.repository.finance;

import com.doomole.portfolio.entity.finance.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {

    Optional<Stock> findByCode(String code);

    List<Stock> findAllByRecommendedTrue();

    @Query("SELECT s FROM Stock s GROUP BY s.sector")
    List<Stock> selectBySectorGrouped();

    List<Stock> findBySectorOrderByScoreDesc(String sector);

    List<Stock> findByScoreGreaterThanEqualOrderByScoreDesc(int score);

    Optional<Stock> findByStockUid(long findByStockUid);
}
