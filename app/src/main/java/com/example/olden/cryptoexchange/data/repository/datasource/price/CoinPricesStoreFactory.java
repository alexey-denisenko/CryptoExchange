package com.example.olden.cryptoexchange.data.repository.datasource.price;


import com.example.olden.cryptoexchange.data.network.CryptoCompareApi;
import com.example.olden.cryptoexchange.data.repository.cache.CoinPricesCache;
import com.example.olden.cryptoexchange.di.Qualifiers;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import io.reactivex.Scheduler;

@Singleton
public class CoinPricesStoreFactory {

    private CryptoCompareApi cryptoCompareApi;
    private CoinPricesCache coinPricesCache;
    private Scheduler ioThread;

    @Inject
    CoinPricesStoreFactory(CryptoCompareApi cryptoCompareApi,
                           CoinPricesCache cache,
                           @Named(Qualifiers.IO_THREAD) Scheduler ioThread) {

        this.cryptoCompareApi = cryptoCompareApi;
        this.coinPricesCache = cache;
        this.ioThread = ioThread;
    }

    public CoinPricesDataStore create(String coin) {
        CoinPricesDataStore coinPricesDataStore;

        if (coinPricesCache.isCached(coin) && !coinPricesCache.isExpired()) {
            coinPricesDataStore = new InMemoryCoinPricesDataStore(coinPricesCache);
        } else {
            coinPricesDataStore = createServerStore();
        }

        return coinPricesDataStore;
    }

    private CoinPricesDataStore createServerStore() {
        return new ServerCoinPricesDataStore(cryptoCompareApi, coinPricesCache, ioThread);
    }
}
