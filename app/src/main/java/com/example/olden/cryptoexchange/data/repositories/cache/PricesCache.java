package com.example.olden.cryptoexchange.data.repositories.cache;

import com.example.olden.cryptoexchange.business.prices.IPricesInteractor;
import com.example.olden.cryptoexchange.data.network.models.response.PricesData;

public class PricesCache {

    private PricesData prices;

    private long cachingTime;

    private final long expirationThresholdInMillis = Math.round(IPricesInteractor.UPDATE_PERIOD * 0.8);

    public PricesData getPrices() {
        return prices;
    }

    public void setPrices(PricesData prices) {
        cachingTime = System.currentTimeMillis();
        this.prices = prices;
    }

    public boolean isCacheExists() {
        return prices != null;
    }

    public boolean isCacheUpToDate() {

        long currentTime = System.currentTimeMillis();
        long difference = currentTime - cachingTime;

        return difference <= expirationThresholdInMillis;
    }
}
