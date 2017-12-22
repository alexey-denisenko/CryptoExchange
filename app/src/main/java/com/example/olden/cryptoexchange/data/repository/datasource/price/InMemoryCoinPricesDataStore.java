package com.example.olden.cryptoexchange.data.repository.datasource.price;

import com.example.olden.cryptoexchange.data.entity.Price;
import com.example.olden.cryptoexchange.data.repository.cache.CoinPricesCache;

import java.util.List;

import io.reactivex.Observable;

public class InMemoryCoinPricesDataStore implements CoinPricesDataStore {

    private CoinPricesCache cache;

    InMemoryCoinPricesDataStore(CoinPricesCache cache) {
        this.cache = cache;
    }

    @Override
    public Observable<List<Price>> getPricesData(String from, List<String> to) {
        return Observable.just(cache.get(from, to));
    }
}
