package com.example.olden.cryptoexchange.data.repository.cache;


import com.example.olden.cryptoexchange.data.entity.CoinsData;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CoinsDataCache {

    private CoinsData coinsData;

    private boolean isCacheUpToDate = false;

    @Inject
    public CoinsDataCache() {}

    public CoinsData get() {
        return coinsData;
    }

    public void set(CoinsData coinsData) {
        this.coinsData = coinsData;
        setUpToDate(true);
    }

    public boolean isCached() {
        return coinsData != null;
    }

    public boolean isExpired() {
        return isCacheUpToDate;
    }

    private void setUpToDate(boolean cacheUpToDate) {
        isCacheUpToDate = cacheUpToDate;
    }

    public void expire() {
        setUpToDate(false);
    }
}
