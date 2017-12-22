package com.example.olden.cryptoexchange.data.repository.datasource.coins;


import com.example.olden.cryptoexchange.data.entity.CoinsData;

import io.reactivex.Observable;

public interface CoinsDataStore {

    Observable<CoinsData> getCoinsData();

}
