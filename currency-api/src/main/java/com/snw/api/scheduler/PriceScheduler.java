package com.snw.api.scheduler;

import com.snw.api.dto.Price;
import com.snw.api.kafka.PriceStreamer;
import com.snw.api.service.CrawlerService;
import com.snw.api.service.PriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PriceScheduler {

    private final PriceService priceService;
    private final PriceStreamer priceStreamer;
    private final CrawlerService crawlerService;

    public PriceScheduler(PriceService priceService, PriceStreamer priceStreamer, CrawlerService crawlerService) {
        this.priceService = priceService;
        this.priceStreamer = priceStreamer;
        this.crawlerService = crawlerService;
    }

    @Value("${app.crawler.url}")
    private String url;

    @Scheduled(cron = "*/5 * * * * *") // every 5 seconds
    public void streamNewPrice() {
            Price price = priceService.getLastPrice();
            price = crawlerService.crawlDollarPrice(url);
            priceService.savePrice(price);
            priceStreamer.send(price);
    }
}
