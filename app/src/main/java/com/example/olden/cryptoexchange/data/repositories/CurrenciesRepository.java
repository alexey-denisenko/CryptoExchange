package com.example.olden.cryptoexchange.data.repositories;


import android.util.Log;

import com.example.olden.cryptoexchange.data.network.api.CryptoCompareService;
import com.example.olden.cryptoexchange.data.network.models.response.CoinsData;
import com.example.olden.cryptoexchange.data.network.models.response.PricesData;
import com.example.olden.cryptoexchange.data.repositories.cache.CurrenciesCache;
import com.example.olden.cryptoexchange.data.repositories.cache.PricesCache;
import com.example.olden.cryptoexchange.other.preferences.StringSetPreferenceType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.reactivex.Single;

public class CurrenciesRepository implements ICurrenciesRepository {

    private static final String TAG = "CurrenciesRepository";

    private CryptoCompareService cryptoCompareService;

    private StringSetPreferenceType stringSetPreferenceType;

    private CurrenciesCache currenciesCache;
    private PricesCache pricesCache;

    public CurrenciesRepository(CryptoCompareService cryptoCompareService, StringSetPreferenceType stringSetPreferenceType, CurrenciesCache currenciesCache, PricesCache pricesCache) {

        this.cryptoCompareService = cryptoCompareService;
        this.stringSetPreferenceType = stringSetPreferenceType;
        this.currenciesCache = currenciesCache;
        this.pricesCache = pricesCache;
    }

    @Override
    public Single<CoinsData> getCoinsData() {

        if (currenciesCache.isCurrenciesListCached() && currenciesCache.isCacheUpToDate()) {
            Log.d(TAG, "getCoinsData: cache");
            return Single.just(currenciesCache.getCurrenciesList());
        }

        return getFromRemoteSourceAndCache();
    }

    @Override
    public Single<PricesData> getPrices(String from, List<String> to) {
        return cryptoCompareService.getPrices(from, to);
    }

    @Override
    public void saveSelectedCurrencies(Set<String> currencies) {
        stringSetPreferenceType.set(currencies);
    }

    @Override
    public Set<String> getSelectedCurrencies() {

        if (stringSetPreferenceType.isSet()) {
            return stringSetPreferenceType.get();
        }

        return new HashSet<>();
    }

    private Single<CoinsData> getFromRemoteSourceAndCache() {
        Log.d(TAG, "getFromRemoteSourceAndCache: server");
        return cryptoCompareService.getCoinsData()
                .doOnSuccess(coinsData -> {
                    currenciesCache.setCurrenciesList(coinsData);
                    currenciesCache.setCacheUpToDate(true);
                });
    }
}
