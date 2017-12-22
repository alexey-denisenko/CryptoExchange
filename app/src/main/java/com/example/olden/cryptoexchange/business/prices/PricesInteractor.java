package com.example.olden.cryptoexchange.business.prices;


import com.example.olden.cryptoexchange.data.entity.Price;
import com.example.olden.cryptoexchange.data.repository.ICoinsRepository;
import com.example.olden.cryptoexchange.di.Qualifiers;
import com.example.olden.cryptoexchange.di.scope.PricesScope;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;


@PricesScope
public class PricesInteractor implements IPricesInteractor {

    private ICoinsRepository repository;
    private Scheduler uiThread;

    @Inject
    public PricesInteractor(ICoinsRepository repository,
                            @Named(Qualifiers.UI_THREAD) Scheduler uiThread) {

        this.repository = repository;
        this.uiThread = uiThread;
    }

    @Override
    public Observable<List<Price>> getUpdatablePrices(String from, List<String> to) {
        return Observable.interval(INITIAL_DELAY, UPDATE_PERIOD, TimeUnit.MILLISECONDS) //Todo use something better
                .flatMap(time -> repository.getPrices(from, to))
                .observeOn(uiThread)
                .retry();
    }
}
