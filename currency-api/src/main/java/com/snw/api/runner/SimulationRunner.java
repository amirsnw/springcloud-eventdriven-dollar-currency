package com.snw.api.runner;

import com.snw.api.dto.Price;
import com.snw.api.service.PriceService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class SimulationRunner implements CommandLineRunner {

    private final PriceService priceService;

    public SimulationRunner(PriceService priceService) {
        this.priceService = priceService;
    }

    @Override
    public void run(String... args) {
        priceService.savePrice(INITIAL_PRICE);
    }

    private static final Price INITIAL_PRICE = new Price(BigDecimal.valueOf(0), LocalDateTime.now());
}
