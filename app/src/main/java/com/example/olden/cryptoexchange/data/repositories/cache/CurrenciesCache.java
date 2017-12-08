package com.example.olden.cryptoexchange.data.repositories.cache;


import com.example.olden.cryptoexchange.data.network.models.response.CoinsData;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CurrenciesCache {

    private CoinsData coinsData;

    private boolean isCacheUpToDate = false;

    @Inject
    public CurrenciesCache() {}

    public CoinsData getCurrenciesList() {
        return coinsData;
    }

    public void setCurrenciesList(CoinsData currenciesList) {
        this.coinsData = currenciesList;
    }

    public boolean isCurrenciesListCached() {
        return coinsData != null;
    }

    public boolean isCacheUpToDate() {
        return isCacheUpToDate;
    }

    public void setCacheUpToDate(boolean cacheUpToDate) {
        isCacheUpToDate = cacheUpToDate;
    }
}
