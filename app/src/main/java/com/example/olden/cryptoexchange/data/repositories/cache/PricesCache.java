package com.example.olden.cryptoexchange.data.repositories.cache;

import com.example.olden.cryptoexchange.business.prices.IPricesInteractor;
import com.example.olden.cryptoexchange.data.network.models.response.Price;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PricesCache {

    private List<Price> prices;

    private long cachingTime;

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
        cachingTime = new Date().getTime();
    }

    public boolean isCacheExists() {
        return prices != null;
    }

    public boolean isCacheUpToDate() {
        long currentTime = new Date().getTime();
        return TimeUnit.MILLISECONDS.toSeconds(currentTime - cachingTime) > IPricesInteractor.UPDATE_PERIOD;
    }
}
