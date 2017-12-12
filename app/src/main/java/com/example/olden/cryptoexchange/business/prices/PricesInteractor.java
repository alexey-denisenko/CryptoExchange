package com.example.olden.cryptoexchange.business.prices;


import com.example.olden.cryptoexchange.data.entity.PricesData;
import com.example.olden.cryptoexchange.data.repository.ICoinsRepository;
import com.example.olden.cryptoexchange.di.scope.PricesScope;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;


@PricesScope
public class PricesInteractor implements IPricesInteractor {

    private ICoinsRepository repository;

    @Inject
    public PricesInteractor(ICoinsRepository repository) {
        this.repository = repository;
    }

    @Override
    public Observable<PricesData> getUpdatablePrices(String from, List<String> to) {
//        return Observable.interval(INITIAL_DELAY, UPDATE_PERIOD, TimeUnit.MILLISECONDS) //Todo use something better
//                .flatMap(time -> repository.getPrices(from, to).toObservable())
//                .retry();
        return null;
    }
}
