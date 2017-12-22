package com.example.olden.cryptoexchange.data.repository.datasource.price;


import com.example.olden.cryptoexchange.data.entity.Price;
import com.example.olden.cryptoexchange.data.entity.PricesData;
import com.example.olden.cryptoexchange.data.network.CryptoCompareApi;
import com.example.olden.cryptoexchange.data.repository.cache.CoinPricesCache;
import com.example.olden.cryptoexchange.di.Qualifiers;

import java.util.List;

import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class ServerCoinPricesDataStore implements CoinPricesDataStore {

    private CryptoCompareApi cryptoCompareApi;
    private CoinPricesCache cache;
    private Scheduler ioThread;

    ServerCoinPricesDataStore(CryptoCompareApi cryptoCompareApi,
                              CoinPricesCache cache,
                              @Named(Qualifiers.IO_THREAD) Scheduler ioThread) {

        this.cryptoCompareApi = cryptoCompareApi;
        this.cache = cache;
        this.ioThread = ioThread;
    }

    @Override
    public Observable<List<Price>> getPricesData(String from, List<String> to) {
        return cryptoCompareApi.getPrices(from, to)
                .map(PricesData::data);
    }
}
