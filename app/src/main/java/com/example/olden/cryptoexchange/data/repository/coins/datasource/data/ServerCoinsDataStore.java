package com.example.olden.cryptoexchange.data.repository.coins.datasource.data;

import com.example.olden.cryptoexchange.data.entity.CoinsData;
import com.example.olden.cryptoexchange.data.network.CryptoCompareApi;
import com.example.olden.cryptoexchange.data.repository.coins.CoinsDataCache;
import com.example.olden.cryptoexchange.di.Qualifiers;

import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class ServerCoinsDataStore implements CoinsDataStore {

    private CryptoCompareApi cryptoCompareApi;
    private CoinsDataCache coinsDataCache;
    private Scheduler ioThread;

    public ServerCoinsDataStore(CryptoCompareApi cryptoCompareApi,
                                CoinsDataCache coinsDataCache,
                                @Named(Qualifiers.IO_THREAD) Scheduler ioThread) {

        this.cryptoCompareApi = cryptoCompareApi;
        this.coinsDataCache = coinsDataCache;
        this.ioThread = ioThread;
    }

    @Override
    public Observable<CoinsData> getCoinsData() {
        return cryptoCompareApi.getCoinsData()
                .doOnNext(coinsData -> coinsDataCache.set(coinsData))
                .subscribeOn(ioThread);
    }
}
