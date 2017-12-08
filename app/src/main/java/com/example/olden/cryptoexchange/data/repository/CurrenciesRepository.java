package com.example.olden.cryptoexchange.data.repository;


import com.example.olden.cryptoexchange.data.network.CryptoCompareApi;
import com.example.olden.cryptoexchange.data.entity.CoinsData;
import com.example.olden.cryptoexchange.data.entity.PricesData;
import com.example.olden.cryptoexchange.data.repository.cache.CurrenciesCache;
import com.example.olden.cryptoexchange.data.repository.cache.PricesCache;
import com.example.olden.cryptoexchange.other.preferences.StringSetPreferenceType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class CurrenciesRepository implements ICurrenciesRepository {

    private CryptoCompareApi cryptoCompareApi;

    private StringSetPreferenceType stringSetPreferenceType;

    private CurrenciesCache currenciesCache;
    private PricesCache pricesCache;

    @Inject
    public CurrenciesRepository(CryptoCompareApi cryptoCompareApi, StringSetPreferenceType stringSetPreferenceType, CurrenciesCache currenciesCache, PricesCache pricesCache) {

        this.cryptoCompareApi = cryptoCompareApi;
        this.stringSetPreferenceType = stringSetPreferenceType;
        this.currenciesCache = currenciesCache;
        this.pricesCache = pricesCache;
    }

    @Override
    public Single<CoinsData> getCoinsData() {

        if (currenciesCache.isCurrenciesListCached() && currenciesCache.isCacheUpToDate()) {
            return Single.just(currenciesCache.getCurrenciesList());
        }

        return getCurrenciesFromRemoteSourceAndCache();
    }

    @Override
    public Single<PricesData> getPrices(String from, List<String> to) {

        if (pricesCache.isCacheUpToDate() && pricesCache.isCacheExists()) {
            return Single.just(pricesCache.getPrices());
        }

        return getPricesFromRemoteSourceAndCache(from, to);
    }

    @Override
    public void saveSelectedCurrencies(Set<String> currencies) {
        stringSetPreferenceType.set(currencies);
    }

    @Override
    public Set<String> getSelectedCurrencies() {

        if (stringSetPreferenceType.contains()) {
            return stringSetPreferenceType.get();
        }

        return new HashSet<>();
    }

    @Override
    public void refreshCurrencies() {
        currenciesCache.setCacheUpToDate(false);
    }

    private Single<CoinsData> getCurrenciesFromRemoteSourceAndCache() {

        return cryptoCompareApi.getCoinsData()
                .doOnSuccess(coinsData -> {
                    currenciesCache.setCurrenciesList(coinsData);
                    currenciesCache.setCacheUpToDate(true);
                });
    }

    private Single<PricesData> getPricesFromRemoteSourceAndCache(String from, List<String> to) {

        return cryptoCompareApi.getPrices(from, to)
                .doOnSuccess(pricesData -> {
                    pricesCache.setPrices(pricesData);
                });
    }
}
