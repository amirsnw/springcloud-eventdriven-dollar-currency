package com.snw.api.config;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    @Valid
    private final CrawlerProperties crawler = new CrawlerProperties();

    @Data
    @Validated
    @Component
    public static class CrawlerProperties {

        @Min(0)
        private int defaultDepth;

        @Min(0)
        private int maxDepthAllowed;

        @Min(5)
        private int timeOut;

        private boolean followRedirects;
    }
}