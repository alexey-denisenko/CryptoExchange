package com.example.olden.cryptoexchange.business.prices;


import com.example.olden.cryptoexchange.data.entity.Price;

import java.util.List;

import io.reactivex.Observable;


public interface IPricesInteractor {

    int UPDATE_PERIOD = 5 * 1000;

    int INITIAL_DELAY = 0;

    Observable<List<Price>> getUpdatablePrices(String from, List<String> to);
}
