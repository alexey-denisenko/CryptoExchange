package com.example.olden.cryptoexchange.data.repository.coins;


import com.example.olden.cryptoexchange.data.mapper.CoinsDataMapper;
import com.example.olden.cryptoexchange.data.repository.coins.datasource.data.CoinsDataStore;
import com.example.olden.cryptoexchange.data.repository.coins.datasource.data.CoinsDataStoreFactory;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class CoinsRepository implements ICoinsRepository {

    private CoinsDataMapper coinsDataMapper;

    private CoinsDataStoreFactory coinsDataStoreFactory;


    @Inject
    public CoinsRepository(CoinsDataStoreFactory coinsDataStoreFactory,
                           CoinsDataMapper coinsDataMapper) {

        this.coinsDataStoreFactory = coinsDataStoreFactory;
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
        return null;
    }

    @Override
    public void saveSelectedCoins(Set<String> currencies) {

    }


}
