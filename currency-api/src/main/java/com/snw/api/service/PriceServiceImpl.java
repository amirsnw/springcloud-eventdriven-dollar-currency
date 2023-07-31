package com.snw.api.service;

import com.snw.api.dto.Price;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PriceServiceImpl extends PriceService {

    @Override
    public Price getLastPrice() {
        return price;
    }

    @Override
    public Price savePrice(Price price) {
        this.price = price;
        return this.price;
    }
}
