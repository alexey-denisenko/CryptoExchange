package com.example.olden.cryptoexchange.data.repository;


import com.example.olden.cryptoexchange.data.mapper.CoinsDataMapper;
import com.example.olden.cryptoexchange.data.repository.datasource.CoinsDataStore;
import com.example.olden.cryptoexchange.data.repository.datasource.CoinsDataStoreFactory;
import com.example.olden.cryptoexchange.other.preferences.StringSetPreferenceType;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class CoinsRepository implements ICoinsRepository {

    private CoinsDataStoreFactory coinsDataStoreFactory;
    private StringSetPreferenceType stringSetPrefs;
    private CoinsDataMapper coinsDataMapper;

    @Inject
    public CoinsRepository(CoinsDataStoreFactory coinsDataStoreFactory,
                           StringSetPreferenceType stringSetPrefs,
                           CoinsDataMapper coinsDataMapper) {

        this.coinsDataStoreFactory = coinsDataStoreFactory;
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
        return Observable.just(stringSetPrefs.get());
    }

    @Override
    public void saveSelectedCoins(Set<String> coins) {
        stringSetPrefs.set(coins);
    }

    @Override
    public void saveSelectedCoins(String coin) {
        stringSetPrefs.add(coin);
    }
}
