package com.example.olden.cryptoexchange.data.repository.coins.datasource;


import com.example.olden.cryptoexchange.data.entity.CoinsData;
import com.example.olden.cryptoexchange.data.repository.cache.CoinsDataCache;
import com.example.olden.cryptoexchange.data.repository.datasource.CoinsDataStore;

import io.reactivex.Observable;

public class InMemoryCoinsDataStore implements CoinsDataStore {

    private CoinsDataCache coinsDataCache;

    public InMemoryCoinsDataStore(CoinsDataCache coinsDataCache) {

        this.coinsDataCache = coinsDataCache;
    }

    @Override
    public Observable<CoinsData> getCoinsData() {
        return Observable.just(coinsDataCache.get());
    }


}
