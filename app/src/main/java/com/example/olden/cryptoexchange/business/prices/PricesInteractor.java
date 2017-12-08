package com.example.olden.cryptoexchange.business.prices;


import com.example.olden.cryptoexchange.data.network.models.response.PricesData;
import com.example.olden.cryptoexchange.data.repositories.ICurrenciesRepository;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;


public class PricesInteractor implements IPricesInteractor {

    private ICurrenciesRepository repository;

    public PricesInteractor(ICurrenciesRepository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<PricesData> getUpdatablePrices(String from, List<String> to) {
        return Observable.interval(INITIAL_DELAY, UPDATE_PERIOD, TimeUnit.MILLISECONDS) //Todo use something better
                .flatMap(time -> repository.getPrices(from, to).toObservable())
                .retry();
    }
}
