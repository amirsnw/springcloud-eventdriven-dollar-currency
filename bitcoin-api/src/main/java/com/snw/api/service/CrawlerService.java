package com.snw.api.service;

import com.snw.api.dto.Price;

public interface CrawlerService {

    /**
     * Crawl the page by a specific depth
     * @param url web page url to crawl
     * @return page info upto desired or max depth
     */
    public Price crawlDollarPrice(final String url);
}