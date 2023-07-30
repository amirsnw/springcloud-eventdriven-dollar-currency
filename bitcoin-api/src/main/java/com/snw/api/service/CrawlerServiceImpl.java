package com.snw.api.service;

import com.snw.api.config.AppProperties;
import com.snw.api.kafka.PriceStreamer;
import com.snw.api.dto.Price;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Slf4j
@Service
public class CrawlerServiceImpl implements CrawlerService {

    private final AppProperties.CrawlerProperties crawlerProperties;

    public CrawlerServiceImpl(PriceStreamer priceStreamer, AppProperties.CrawlerProperties crawlerProperties) {
        this.crawlerProperties = crawlerProperties;
    }


    /*@Override
    @Cacheable("web-crawler-service")
    public PageTreeInfo deepCrawl(final String url, final int depth) {

        if (depth < 0) {
            return null;
        } else {
            return innerDeepCrawl(url, depth, null);
        }
    }

    private PageTreeInfo innerDeepCrawl(final String url, final int depth, final List<String> processedUrls) {

        final List<String> updatedProcessedUrls = Optional.ofNullable(processedUrls).orElse(new ArrayList<>());
        if (!updatedProcessedUrls.contains(url)) {
            updatedProcessedUrls.add(url);
            log.info("fetched {}", url);
            final PageTreeInfo pageTreeInfo = new PageTreeInfo(url);
            crawl(url).ifPresent(pageInfo -> {
                pageTreeInfo.setTitle(pageInfo.getTitle());
                pageTreeInfo.setValid(true);
                pageInfo.getLinks().forEach(link -> {
                    pageTreeInfo.addNodesItem(innerDeepCrawl(link.attr("abs:href"), depth - 1, updatedProcessedUrls));
                });
            });
            return pageTreeInfo;
        } else {
            return null;
        }
    }

    *//*
     * Method to fetch web page content. Cache is used for better performance
     *//*
    @Override
    @Cacheable("web-crawler-service")
    public Optional<PageInfo> crawl(final String url) {
        try {
            final Document doc = Jsoup.connect(url).timeout(crawlerProperties.getTimeOut()).followRedirects(crawlerProperties.isFollowRedirects()).get();

            final Elements links = doc.select("a[href]");
            final String title = doc.title();
            return Optional.of(new PageInfo(title, url, links));
        } catch (final IOException | IllegalArgumentException e) {
            return Optional.empty();
        }
    }*/

    @Override
    // @Cacheable("web-crawler-service")
    public Price crawlDollarPrice(final String url) {
        try {
            final Document doc = Jsoup.connect(url)
                    .timeout(crawlerProperties.getTimeOut())
                    .followRedirects(crawlerProperties.isFollowRedirects())
                    .get();
            final Elements priceEl = doc.select("span[data-col=\"info.last_trade.PDrCotVal\"]");
            return new Price(new BigDecimal(priceEl.get(0).html().replace(",",".")),
                    LocalDateTime.now());
        } catch (final IOException | IllegalArgumentException e) {
            return null;
        }
    }
}