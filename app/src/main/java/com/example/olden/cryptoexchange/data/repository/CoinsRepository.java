package com.example.olden.cryptoexchange.data.repository;


import com.example.olden.cryptoexchange.data.entity.Price;
import com.example.olden.cryptoexchange.data.mapper.CoinsDataMapper;
import com.example.olden.cryptoexchange.data.repository.datasource.coins.CoinsDataStore;
import com.example.olden.cryptoexchange.data.repository.datasource.coins.CoinsDataStoreFactory;
import com.example.olden.cryptoexchange.data.repository.datasource.price.CoinPricesStoreFactory;
import com.example.olden.cryptoexchange.other.preferences.StringSetPreferenceType;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class CoinsRepository implements ICoinsRepository {

    private CoinsDataStoreFactory coinsDataStoreFactory;
    private CoinPricesStoreFactory coinPricesStoreFactory;
    private StringSetPreferenceType stringSetPrefs;
    private CoinsDataMapper coinsDataMapper;

    @Inject
    public CoinsRepository(CoinsDataStoreFactory coinsDataStoreFactory,
                           CoinPricesStoreFactory coinPricesStoreFactory, StringSetPreferenceType stringSetPrefs,
                           CoinsDataMapper coinsDataMapper) {

        this.coinsDataStoreFactory = coinsDataStoreFactory;
        this.coinPricesStoreFactory = coinPricesStoreFactory;
        this.stringSetPrefs = stringSetPrefs;
        this.coinsDataMapper = coinsDataMapper;
    }

    @Override
    public Observable<List<String>> getCoinsData() {

        CoinsDataStore coinsDataStore = coinsDataStoreFactory.create();
        return coinsDataStore.getCoinsData()
                .map(coinsData -> coinsDataMapper.transform(coinsData));
    }

    @Override
    public Observable<List<String>> refreshCoinsData() {

        CoinsDataStore coinsDataStore = coinsDataStoreFactory.createServerStore();
        return coinsDataStore.getCoinsData()
                .map(coinsData -> coinsDataMapper.transform(coinsData));
    }

    @Override
    public Observable<Set<String>> getSelectedCoins() {
        Set<String> result = stringSetPrefs.get();

        return result != null? Observable.just(result) : Observable.empty() ;
    }

    @Override
    public void saveSelectedCoins(Set<String> coins) {
        stringSetPrefs.set(coins);
    }

    @Override
    public void saveSelectedCoins(String coin) {
        stringSetPrefs.add(coin);
    }

    @Override
    public Observable<List<Price>> getPrices(String from, List<String> to) {
        return coinPricesStoreFactory.create(from).getPricesData(from, to);
    }
}
