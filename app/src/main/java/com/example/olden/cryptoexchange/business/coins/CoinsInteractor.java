package com.example.olden.cryptoexchange.business.coins;


import com.example.olden.cryptoexchange.data.repository.ICoinsRepository;
import com.example.olden.cryptoexchange.di.Qualifiers;
import com.example.olden.cryptoexchange.di.scope.CoinsListScope;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

@CoinsListScope
public class CoinsInteractor implements ICoinsInteractor {

    private ICoinsRepository iCoinsRepository;
    private Scheduler uiThread;

    @Inject
    public CoinsInteractor(ICoinsRepository iCoinsRepository,
                           @Named(Qualifiers.UI_THREAD) Scheduler uiThread) {

        this.iCoinsRepository = iCoinsRepository;
        this.uiThread = uiThread;
    }

    @Override
    public Observable<List<String>> getCoinNamesList(boolean forceRefresh) {

        Observable<List<String>> result;

        if (forceRefresh) {
            result = iCoinsRepository.refreshCoinsData();
        } else {
            result = iCoinsRepository.getCoinsData();
        }

        Observable<List<String>> selectedCoinsObservable = getSelectedCoinsList();

        return result
                .withLatestFrom(selectedCoinsObservable, (coinsList, selectedCoins) -> {
                    coinsList.removeAll(selectedCoins);
                    return coinsList;
                })
                .observeOn(uiThread);
    }

    @Override
    public Observable<List<String>> getSelectedCoinsList() {
        return iCoinsRepository.getSelectedCoins()
                .map(ArrayList::new);
    }

    @Override
    public void saveSelectedCoins(List<String> coins) {
        Set<String> currenciesSet = new HashSet<>(coins);
        iCoinsRepository.saveSelectedCoins(currenciesSet);
    }

    @Override
    public void saveSelectedCoins(String coin) {
        iCoinsRepository.saveSelectedCoins(coin);
    }
}
