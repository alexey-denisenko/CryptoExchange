package com.example.olden.cryptoexchange.data.repository.datasource;


import com.example.olden.cryptoexchange.data.network.CryptoCompareApi;
import com.example.olden.cryptoexchange.data.repository.cache.CoinsDataCache;
import com.example.olden.cryptoexchange.di.Qualifiers;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import io.reactivex.Scheduler;

@Singleton
public class CoinsDataStoreFactory {

    private CoinsDataCache coinsDataCache;
    private CryptoCompareApi cryptoCompareApi;
    private Scheduler ioThread;

    @Inject
    public CoinsDataStoreFactory(CoinsDataCache coinsDataCache,
                                 CryptoCompareApi cryptoCompareApi,
                                 @Named(Qualifiers.IO_THREAD) Scheduler ioThread) {

        this.coinsDataCache = coinsDataCache;
        this.cryptoCompareApi = cryptoCompareApi;
        this.ioThread = ioThread;
    }

    public CoinsDataStore create() {
        CoinsDataStore coinsDataStore;

        if (coinsDataCache.isCached() && !coinsDataCache.isExpired()) {
            coinsDataStore = new InMemoryCoinsDataStore(coinsDataCache);
        } else {
            coinsDataStore = createServerStore();
        }

        return coinsDataStore;
    }

    public CoinsDataStore createServerStore() {

        return new ServerCoinsDataStore(cryptoCompareApi, coinsDataCache, ioThread);
    }
}
