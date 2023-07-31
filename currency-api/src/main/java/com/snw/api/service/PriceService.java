package com.snw.api.service;

import com.snw.api.dto.Price;

public abstract class PriceService {

    protected Price price;

    public abstract Price getLastPrice();

    public abstract Price savePrice(Price price);
}
