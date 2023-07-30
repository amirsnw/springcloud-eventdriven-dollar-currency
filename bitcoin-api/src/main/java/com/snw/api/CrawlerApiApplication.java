package com.snw.api;

import com.snw.api.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
@EnableCaching
public class CrawlerApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrawlerApiApplication.class, args);
    }
}
