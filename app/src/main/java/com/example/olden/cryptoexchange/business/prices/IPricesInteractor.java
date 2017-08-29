package com.example.olden.cryptoexchange.business.prices;


import com.example.olden.cryptoexchange.data.network.models.response.PricesData;

import java.util.List;

import io.reactivex.Observable;


public interface IPricesInteractor {

    int UPDATE_PERIOD = 5;

    Observable<PricesData> getUpdatablePrices(String from, List<String> to);
}
