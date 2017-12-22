package com.example.olden.cryptoexchange.data.repository.datasource.price;


import com.example.olden.cryptoexchange.data.entity.Price;

import java.util.List;

import io.reactivex.Observable;

public interface CoinPricesDataStore {

    Observable<List<Price>> getPricesData(String from, List<String> to);
}
