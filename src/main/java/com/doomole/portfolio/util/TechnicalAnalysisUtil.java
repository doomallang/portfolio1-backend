package com.doomole.portfolio.util;

import com.doomole.portfolio.dto.response.finance.ResStockPrice;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class TechnicalAnalysisUtil {
    public static void calculateIndicators(List<ResStockPrice> prices) {
        Double ema12 = null;
        Double ema26 = null;
        final double k12 = 2.0 / (12 + 1);
        final double k26 = 2.0 / (26 + 1);
        final double k9 = 2.0 / (9 + 1);
        Double prevSignal = null;

        for (int i = 0; i < prices.size(); i++) {
            ResStockPrice p = prices.get(i);
            int score = 0;

            if (i >= 4) p.setMa5(average(prices, i, 5));
            if (i >= 9) p.setMa10(average(prices, i, 10));
            if (i >= 19) {
                double ma20 = average(prices, i, 20);
                double std = stddev(prices, i, 20, ma20);
                p.setMa20(ma20);
                p.setUpper(ma20 + 2 * std);
                p.setLower(ma20 - 2 * std);
            }

            if (i >= 14) {
                double rsi = calculateRSI(prices, i, 14);
                p.setRsi(rsi);
                if (rsi > 50 && rsi < 70) score += 1;
            }

            if (i > 0) {
                double prevClose = prices.get(i - 1).getClose();
                if (prevClose != 0) {
                    double ret = (p.getClose() - prevClose) * 100.0 / prevClose;
                    p.setRet(ret);
                    if (ret > 1.5) score += 1;
                }
            }

            if (i > 0) {
                ResStockPrice prev = prices.get(i - 1);
                if (p.getMa5() != null && p.getMa10() != null && prev.getMa5() != null && prev.getMa10() != null) {
                    boolean goldenCross = prev.getMa5() < prev.getMa10() && p.getMa5() > p.getMa10();
                    p.setGoldenCross(goldenCross);
                    if (goldenCross) score += 1;
                } else {
                    p.setGoldenCross(false);
                }
            }

            double close = p.getClose();
            ema12 = (ema12 == null) ? close : (close - ema12) * k12 + ema12;
            ema26 = (ema26 == null) ? close : (close - ema26) * k26 + ema26;
            double macd = ema12 - ema26;
            p.setMacd(macd);

            if (prevSignal == null) prevSignal = macd;
            double signal = (macd - prevSignal) * k9 + prevSignal;
            p.setSignal(signal);
            p.setHistogram(macd - signal);
            prevSignal = signal;

            if (macd > signal) score += 1;
            if (macd > 0 && signal > 0 && macd - signal > 0.3) score += 1;

            if (i >= 1) {
                boolean isTrendingUp = prices.get(i).getClose() > prices.get(i - 1).getClose();
                p.setIsTrendingUp(isTrendingUp);
                if (isTrendingUp) score += 1;
            } else {
                p.setIsTrendingUp(false);
            }

            if (p.getMa20() != null && p.getUpper() != null && p.getClose() != null) {
                if (p.getClose() > p.getMa20() && p.getClose() <= p.getUpper()) {
                    score += 1;
                }
            }

            if (p.getMa5() != null && p.getMa20() != null && p.getMa20() != 0) {
                double ratio = p.getMa5() / p.getMa20();
                if (ratio >= 1.003 && ratio <= 1.07) {
                    score += 1;
                }
            }

            if (i >= 3) {
                double avgVol = (prices.get(i - 1).getVolume() + prices.get(i - 2).getVolume() + prices.get(i - 3).getVolume()) / 3.0;
                if (avgVol > 0 && p.getVolume() > avgVol * 1.2) {
                    score += 1;
                }
            }

            if (i >= 3) {
                double avgForeignBuy = (prices.get(i - 1).getForeignBuyVolume() + prices.get(i - 2).getForeignBuyVolume() + prices.get(i - 3).getForeignBuyVolume()) / 3.0;
                if (avgForeignBuy > 0 && p.getForeignBuyVolume() > avgForeignBuy * 1.3) {
                    score += 1;
                }
            }

            p.setScore(score);
        }
    }

    private static double average(List<ResStockPrice> prices, int end, int length) {
        return prices.subList(end - length + 1, end + 1).stream()
                .collect(Collectors.averagingDouble(ResStockPrice::getClose));
    }

    private static double stddev(List<ResStockPrice> prices, int end, int length, double mean) {
        return Math.sqrt(
                prices.subList(end - length + 1, end + 1).stream()
                        .mapToDouble(p -> Math.pow(p.getClose() - mean, 2))
                        .average().orElse(0.0)
        );
    }

    private static double calculateRSI(List<ResStockPrice> prices, int end, int period) {
        double gain = 0.0;
        double loss = 0.0;
        int start = Math.max(end - period + 1, 1);

        for (int i = start; i <= end; i++) {
            double change = prices.get(i).getClose() - prices.get(i - 1).getClose();
            if (change > 0) gain += change;
            else loss -= change;
        }
        if (gain + loss == 0) return 50.0;
        double rs = gain / loss;
        return 100.0 - (100.0 / (1.0 + rs));
    }

    public static int calculateFundamentalScore(Double per, Double pbr, Double roe, Double debtRatio, Double marketCap, Double top30Threshold, Double epsGrowth, Double dividendYield) {
        int score = 0;
        if (per != null && per < 15) score += 1;
        if (pbr != null && pbr < 1.5) score += 1;
        if (roe != null && roe > 8) score += 1;
        if (debtRatio != null && debtRatio < 150) score += 1;
        if (marketCap != null && top30Threshold != null && marketCap >= top30Threshold * 0.8) score += 1;

        if (per != null && epsGrowth != null) {
            double peg = per / (epsGrowth + 0.01);
            if (peg < 1.0) score += 2;
            else if (peg < 1.5) score += 1;
        }

        if (dividendYield != null && dividendYield > 3.0) {
            score += 1;
        }

        return score;
    }

    public static boolean isGoodStock(List<ResStockPrice> prices, Double per, Double pbr, Double roe, Double debtRatio, Double marketCap, Double top30Threshold, Double epsGrowth, Double dividendYield) {
        if (prices == null || prices.isEmpty()) return false;

        int totalScore = calculateTotalScore(prices, per, pbr, roe, debtRatio, marketCap, top30Threshold, epsGrowth, dividendYield);
        boolean result = totalScore >= 9;
        log.info("isGoodStock >> TotalScore={}, Recommended={}", totalScore, result);
        return result;
    }

    public static int calculateTotalScore(List<ResStockPrice> prices, Double per, Double pbr, Double roe, Double debtRatio, Double marketCap, Double top30Threshold, Double epsGrowth, Double dividendYield) {
        if (prices == null || prices.isEmpty()) return 0;

        List<ResStockPrice> valid = prices.stream()
                .filter(p -> p.getMa20() != null && p.getScore() != null)
                .collect(Collectors.toList());

        if (valid.size() < 7) return 0;

        List<ResStockPrice> recent = valid.subList(Math.max(0, valid.size() - 7), valid.size());

        long highScoreDays = recent.stream().filter(p -> p.getScore() >= 3).count();
        if (highScoreDays < 3) return 0;

        double avg = recent.stream().mapToInt(ResStockPrice::getScore).average().orElse(0);
        int max = recent.stream().mapToInt(ResStockPrice::getScore).max().orElse(0);
        double std = Math.sqrt(recent.stream().mapToDouble(p -> Math.pow(p.getScore() - avg, 2)).average().orElse(0));
        double adjustedAvg = avg - (std * 0.5);

        int techScore = (int) Math.round(adjustedAvg * 0.6 + max * 0.4);

        int fundScore = calculateFundamentalScore(per, pbr, roe, debtRatio, marketCap, top30Threshold, epsGrowth, dividendYield);

        return techScore + fundScore;
    }
}
