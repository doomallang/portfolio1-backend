package com.doomole.portfolio.service;

import com.doomole.portfolio.converter.FinanceConverter;
import com.doomole.portfolio.dto.response.finance.ResNews;
import com.doomole.portfolio.dto.response.finance.ResNowStockPrice;
import com.doomole.portfolio.dto.response.finance.ResStock;
import com.doomole.portfolio.dto.response.finance.ResStockPrice;
import com.doomole.portfolio.entity.finance.Stock;
import com.doomole.portfolio.entity.finance.StockPrice;
import com.doomole.portfolio.exception.FailException;
import com.doomole.portfolio.repository.finance.StockPriceRepository;
import com.doomole.portfolio.repository.finance.StockRepository;
import com.doomole.portfolio.util.TechnicalAnalysisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FinanceService {
    private final StockRepository stockRepository;
    private final StockPriceRepository stockPriceRepository;

    @Value("${api.naver.client-id}")
    private String clientId;

    @Value("${api.naver.client-secret}")
    private String clientSecret;

    @Transactional
    public void doStockBatch() {
        log.info("Stock Batch Started");
        Map<String, List<ResStock>> sectorMap = getStockGroupBySector();
        sectorMap.entrySet().parallelStream().forEach(entry -> {
            String sector = entry.getKey();
            List<ResStock> stocks = entry.getValue();
            log.info("sector ========================== {}", sector);
            stocks.parallelStream().forEach(resStock -> {
                try {
                    Stock stock = stockRepository.findByCode(resStock.getCode())
                            .orElseGet(() -> Stock.builder()
                                    .code(resStock.getCode())
                                    .name(resStock.getName())
                                    .sector(sector)
                                    .build());

                    stock.setPer(resStock.getPer());
                    stock.setPbr(resStock.getPbr());
                    stock.setRoe(resStock.getRoe());
                    stock.setDebtRatio(resStock.getDebtRatio());
                    stock.setMarketCap(resStock.getMarketCap());
                    stock.setEpsGrowth(resStock.getEpsGrowth());
                    stock.setDividendYield(resStock.getDividendYield());
                    stockRepository.save(stock);

                    log.info("stock : {}", stock);

                    List<LocalDate> existingDates = stockPriceRepository.findAllByCode(resStock.getCode()).stream()
                            .map(StockPrice::getDate)
                            .toList();

                    List<StockPrice> stockPrices = resStock.getStockPrices().stream()
                            .filter(p -> !existingDates.contains(p.getDate()))
                            .map(p -> StockPrice.builder()
                                    .code(resStock.getCode())
                                    .date(p.getDate())
                                    .open(p.getOpen())
                                    .high(p.getHigh())
                                    .low(p.getLow())
                                    .close(p.getClose())
                                    .volume(p.getVolume())
                                    .ma5(p.getMa5())
                                    .ma10(p.getMa10())
                                    .ma20(p.getMa20())
                                    .upper(p.getUpper())
                                    .lower(p.getLower())
                                    .ret(p.getRet())
                                    .goldenCross(p.getGoldenCross())
                                    .isTrendingUp(p.getIsTrendingUp())
                                    .macd(p.getMacd())
                                    .signal(p.getSignal())
                                    .histogram(p.getHistogram())
                                    .score(p.getScore())
                                    .rsi(p.getRsi())
                                    .foreignBuyVolume(p.getForeignBuyVolume())
                                    .build())
                            .collect(Collectors.toList());

                    stockPriceRepository.saveAll(stockPrices);
                } catch (Exception e) {
                    log.warn("Error processing stock {}: {}", resStock.getCode(), e.getMessage());
                }
            });
        });
        log.info("Stock Batch Finished");
    }

    public void doStockRecommendBatch() {
        log.info("Stock Recommend Batch Started");
        List<Stock> stockList = stockRepository.findAll();
        Double top30Threshold = getTop30MarketCapThreshold();

        for (Stock stock : stockList) {
            List<ResStockPrice> prices = stockPriceRepository.findAllByCode(stock.getCode()).stream()
                    .sorted(Comparator.comparing(StockPrice::getDate))
                    .map(FinanceConverter::toResStockPrice)
                    .toList();

            boolean isGood = TechnicalAnalysisUtil.isGoodStock(
                    prices,
                    stock.getPer(),
                    stock.getPbr(),
                    stock.getRoe(),
                    stock.getDebtRatio(),
                    stock.getMarketCap(),
                    top30Threshold,
                    stock.getEpsGrowth(),
                    stock.getDividendYield()
            );

            int totalScore = TechnicalAnalysisUtil.calculateTotalScore(
                    prices,
                    stock.getPer(),
                    stock.getPbr(),
                    stock.getRoe(),
                    stock.getDebtRatio(),
                    stock.getMarketCap(),
                    top30Threshold,
                    stock.getEpsGrowth(),
                    stock.getDividendYield()
            );

            stock.setRecommended(isGood);
            stock.setScore(totalScore);
            stockRepository.save(stock);
        }
        log.info("Stock Recommend Batch Finished");
    }

    public List<ResStock> getRecommendedStocks() {
        return stockRepository.findAllByRecommendedTrue().stream()
                .map(stock -> {
                    List<ResStockPrice> resStockPriceList = stockPriceRepository.findAllByCode(stock.getCode()).stream()
                            .map(FinanceConverter::toResStockPrice)
                            .toList();

                    ResStock resStock = FinanceConverter.toResStock(stock);
                    resStock.setStockPrices(resStockPriceList);
                    return resStock;
                })
                .toList();
    }

    public List<ResStock> getStockList(String sector) {
        return stockRepository.findBySectorOrderByScoreDesc(sector).stream()
                .map(stock -> {
                    List<ResStockPrice> resStockPriceList = stockPriceRepository.findAllByCode(stock.getCode()).stream()
                            .map(FinanceConverter::toResStockPrice)
                            .toList();

                    ResStock resStock = FinanceConverter.toResStock(stock);
                    resStock.setStockPrices(resStockPriceList);
                    return resStock;
                })
                .toList();
    }

    public ResStock getStock(long stockUid) {
        return stockRepository.findByStockUid(stockUid)
                .map(stock -> {
                    ResStock resStock = FinanceConverter.toResStock(stock);
                    resStock.setStockPrices(stockPriceRepository.findAllByCode(resStock.getCode()).stream()
                            .map(FinanceConverter::toResStockPrice)
                            .toList());
                    return resStock;
                })
                .orElseThrow(() -> new FailException("해당 주식이 존재하지 않습니다."));
    }

    public List<ResStock> getStockRecommendList() {
        List<Stock> stocks = stockRepository.findByScoreGreaterThanEqualOrderByScoreDesc(9);
        List<String> stockCodeList = stocks.stream().map(Stock::getCode).toList();

        Map<String, List<ResStockPrice>> stockPriceMap = stockPriceRepository.findAllByCodeIn(stockCodeList).stream()
                .collect(Collectors.groupingBy(
                        StockPrice::getCode,
                        Collectors.mapping(FinanceConverter::toResStockPrice, Collectors.toList())
                ));

        return stocks.stream()
                .map(stock -> {
                    ResStock resStock = FinanceConverter.toResStock(stock);
                    resStock.setStockPrices(stockPriceMap.getOrDefault(stock.getCode(), List.of()));
                    return resStock;
                })
                .toList();
    }

    public List<String> getStockSectorList() {
        List<String> sectorList = stockRepository.selectBySectorGrouped().stream()
                .map(Stock::getSector)
                .toList();

        return sectorList;
    }

    public ResNowStockPrice getNowStockPrice(String stockCode) {
        try {
            String url = "https://finance.naver.com/item/main.naver?code=" + stockCode;
            Document doc = Jsoup.connect(url).get();

            Elements dtElements = doc.select("dl.blind dt");
            Elements ddElements = doc.select("dl.blind dd");

            String nowPriceText = null;
            String prevPriceText = null;

            for (Element dd : ddElements) {
                String text = dd.text().trim();

                if (text.startsWith("현재가")) {
                    // "현재가 28,850 전일대비 보합 0 0.00 퍼센트"
                    String[] tokens = text.split(" ");
                    if (tokens.length >= 2) {
                        nowPriceText = tokens[1].replace(",", "");
                    }
                } else if (text.startsWith("전일가")) {
                    // "전일가 28,850"
                    String[] tokens = text.split(" ");
                    if (tokens.length >= 2) {
                        prevPriceText = tokens[1].replace(",", "");
                    }
                }
            }
            String diffRate = null;
            String diffPrice = null;

            if (nowPriceText != null && prevPriceText != null) {
                double now = Double.parseDouble(nowPriceText);
                double prev = Double.parseDouble(prevPriceText);
                double diff = now - prev;
                double rate = (diff / prev) * 100;

                diffPrice = (diff >= 0 ? "+" : "") + Math.round(diff);
                diffRate = String.format("%+.2f%%", rate);
            }

            return ResNowStockPrice.builder()
                    .stockCode(stockCode)
                    .price(nowPriceText)
                    .diffPrice(diffPrice)
                    .diffRate(diffRate)
                    .build();
        } catch (IOException e) {
            throw new RuntimeException("실시간 주가 조회 실패", e);
        }
    }

    public List<ResNews> getNewsList(String stockName) {
        String apiURL = "https://openapi.naver.com/v1/search/news.json?query=" + URLEncoder.encode(stockName, StandardCharsets.UTF_8);

        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(apiURL).openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("X-Naver-Client-Id", clientId);
            conn.setRequestProperty("X-Naver-Client-Secret", clientSecret);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String response = br.lines().collect(Collectors.joining());
            br.close();

            // JSON 파싱
            JSONObject json = new JSONObject(response);
            JSONArray items = json.getJSONArray("items");

            List<ResNews> newsList = new ArrayList<>();
            for (int i = 0; i < items.length(); i++) {
                JSONObject item = items.getJSONObject(i);
                ResNews news = ResNews.builder()
                                .title(item.getString("title").replaceAll("<[^>]*>", ""))
                                .link(item.getString("link"))
                                .description(item.getString("description").replaceAll("<[^>]*>", ""))
                                .pubDate(item.getString("pubDate"))
                                .build();
                newsList.add(news);
            }

            return newsList;
        } catch (IOException e) {
            throw new RuntimeException("네이버 뉴스 검색 실패", e);
        }
    }

    private Map<String, List<ResStock>> getStockGroupBySector() {
        log.info("[Stock Batch] getStockGroupBySector started");
        Map<String, List<ResStock>> sectorMap = new HashMap<>();

        try {
            Document doc = Jsoup.connect("https://finance.naver.com/sise/sise_group.naver?type=upjong").get();
            Elements sectorElements = doc.select("table.type_1 a");
            for (var elem : sectorElements) {
                String sectorName = elem.text();
                log.info("================================== 크롤링 업종 : {}", sectorName);
                if(sectorName.equalsIgnoreCase("기타")) continue;

                String link = "https://finance.naver.com" + elem.attr("href");

                List<ResStock> stocks = fetchStocksBySectorPage(link);
                sectorMap.put(sectorName, stocks);
            }
        } catch (Exception e) {
            log.info("getStockGroupBySector : {}", e.getMessage());
        }

        log.info("[Stock Batch] getStockGroupBySector finished");
        return sectorMap;
    }

    private List<ResStock> fetchStocksBySectorPage(String sectorUrl) {
        List<ResStock> stocks = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(sectorUrl).get();
            Elements rows = doc.select("table.type_5 tr");

            for (Element row : rows) {
                Elements cols = row.select("td");
                if (cols.size() < 6) continue;
                Element nameTag = cols.get(0).selectFirst("a");
                if (nameTag == null) continue;

                String name = nameTag.text();
                log.info("크롤링 종목명 : {}", name);
                String code = nameTag.attr("href").split("code=")[1];

                ResStock stock = ResStock.builder().name(name).code(code).build();
                populateFinancialDetails(stock);
                stocks.add(stock);
            }
        } catch (Exception e) {
            log.error("Error fetching stocks from sector page {}: {}", sectorUrl, e.getMessage());
        }

        return stocks;
    }

    private void populateFinancialDetails(ResStock stock) {
        try {
            String detailUrl = "https://finance.naver.com/item/main.naver?code=" + stock.getCode();
            Document doc = Jsoup.connect(detailUrl).get();

            Elements rows = doc.select("table tbody tr");
            for (Element row : rows) {
                String th = row.selectFirst("th") != null ? row.selectFirst("th").text() : "";
                String td = row.selectFirst("td") != null ? row.selectFirst("td").text().replace(",", "").replace("%", "") : "";
                try {
                    if (th.contains("PER")) stock.setPer(Double.parseDouble(td));
                    else if (th.contains("PBR")) stock.setPbr(Double.parseDouble(td));
                    else if (th.contains("ROE")) stock.setRoe(Double.parseDouble(td));
                    else if (th.contains("부채비율")) stock.setDebtRatio(Double.parseDouble(td));
                    else if (th.contains("시가총액")) stock.setMarketCap(Double.parseDouble(td));
                    else if (th.contains("EPS")) stock.setEpsGrowth(Double.parseDouble(td));
                    else if (th.contains("배당수익률")) stock.setDividendYield(Double.parseDouble(td));
                } catch (NumberFormatException ignored) {}
            }

            List<ResStockPrice> prices = fetchDailyPrices(stock.getCode(), 60);
            TechnicalAnalysisUtil.calculateIndicators(prices);
            stock.setStockPrices(prices);

        } catch (Exception e) {
            log.error("Error populating details for {}: {}", stock.getCode(), e.getMessage());
        }
    }

    private List<ResStockPrice> fetchDailyPrices(String code, int days) {
        List<ResStockPrice> prices = new ArrayList<>();
        int page = 1;
        try {
            while (prices.size() < days && page <= 4) {
                String url = "https://finance.naver.com/item/sise_day.naver?code=" + code + "&page=" + page;
                Document doc = Jsoup.connect(url).get();
                Elements rows = doc.select("table.type2 tr");

                for (Element row : rows) {
                    Elements cols = row.select("td");
                    if (cols.size() < 6) continue;
                    try {
                        String dateStr = cols.get(0).text().trim().replace(".", "-");
                        LocalDate date = LocalDate.parse(dateStr);
                        long foreignBuy = Long.parseLong(cols.get(5).text().replace(",", ""));

                        // 기존 종가 데이터도 함께 가져와야 하므로 기존 sise_day도 호출
                        ResStockPrice base = ResStockPrice.builder()
                                .date(date)
                                .close(Integer.parseInt(cols.get(1).text().replace(",", "")))
                                .open(Integer.parseInt(cols.get(3).text().replace(",", "")))
                                .high(Integer.parseInt(cols.get(4).text().replace(",", "")))
                                .low(Integer.parseInt(cols.get(5).text().replace(",", "")))
                                .volume(Long.parseLong(cols.get(6).text().replace(",", "")))
                                .foreignBuyVolume(foreignBuy)
                                .build();
                        if (base != null) {
                            base.setForeignBuyVolume(foreignBuy);
                            prices.add(base);
                            if (prices.size() >= days) break;
                        }
                    } catch (Exception ignored) {}
                }
                page++;
            }
        } catch (Exception e) {
            log.error("Error fetching prices for {}: {}", code, e.getMessage());
        }
        return prices;
    }

    private Double getTop30MarketCapThreshold() {
        List<Double> caps = stockRepository.findAll().stream()
                .map(Stock::getMarketCap)
                .filter(Objects::nonNull)
                .sorted(Comparator.reverseOrder())
                .toList();
        int idx = Math.min((int) (caps.size() * 0.3), caps.size() - 1);
        return caps.get(idx);
    }
}
