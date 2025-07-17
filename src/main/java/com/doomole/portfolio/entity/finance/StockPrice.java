package com.doomole.portfolio.entity.finance;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@Entity
@Table(name = "stock_price")
public class StockPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_price_uid")
    private long stockPriceUid;

    @Column(name = "code")
    private String code;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "open")
    private Integer open;

    @Column(name = "high")
    private Integer high;

    @Column(name = "low")
    private Integer low;

    @Column(name = "close")
    private Integer close;

    @Column(name = "volume")
    private Long volume;

    @Column(name = "ma5")
    private Double ma5;

    @Column(name = "ma10")
    private Double ma10;

    @Column(name = "ma20")
    private Double ma20;

    @Column(name = "macd")
    private Double macd;

    @Column(name = "`signal`")
    private Double signal;

    @Column(name = "histogram")
    private Double histogram;

    @Column(name = "score")
    private Integer score;

    @Column(name = "upper")
    private Double upper;

    @Column(name = "lower")
    private Double lower;

    @Column(name = "ret")
    private Double ret;

    @Column(name = "golden_cross")
    private Boolean goldenCross;

    @Column(name = "is_trending_up")
    private Boolean isTrendingUp;

    @Column(name = "foreign_buy_volume")
    private Long foreignBuyVolume;

    @Column(name = "rsi")
    private Double rsi;
}
