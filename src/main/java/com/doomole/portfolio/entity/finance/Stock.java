package com.doomole.portfolio.entity.finance;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@Entity
@Table(name = "stock")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_uid")
    private long stockUid;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "sector")
    private String sector;

    @Column(name = "per")
    private Double per;

    @Column(name = "pbr")
    private Double pbr;

    @Column(name = "roe")
    private Double roe;

    @Column(name = "debt_ratio")
    private Double debtRatio;

    @Column(name = "market_cap")
    private Double marketCap;

    @Column(name = "recommended")
    private Boolean recommended;

    @Column(name = "score")
    private Integer score;

    @Column(name = "eps_growth")
    private Double epsGrowth;

    @Column(name = "dividend_yield")
    private Double dividendYield;
}
