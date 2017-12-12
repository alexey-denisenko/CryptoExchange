package com.example.olden.cryptoexchange.business.currencies_list;


import com.example.olden.cryptoexchange.data.repository.coins.ICoinsRepository;
import com.example.olden.cryptoexchange.di.Qualifiers;
import com.example.olden.cryptoexchange.di.scope.CurrenciesListScope;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

@CurrenciesListScope
public class CurrenciesInteractor implements ICurrenciesInteractor {

    private ICoinsRepository iCoinsRepository;
    private Scheduler uiThread;

    @Inject
    public CurrenciesInteractor(ICoinsRepository iCoinsRepository,
                                @Named(Qualifiers.UI_THREAD) Scheduler uiThread) {

        this.iCoinsRepository = iCoinsRepository;
        this.uiThread = uiThread;
    }

    @Override
    public Observable<List<String>> getCurrencyNamesList(boolean forceRefresh) {

        Observable<List<String>> result;

        if (forceRefresh) {
            result = iCoinsRepository.refreshCoinsData();
        } else {
            result = iCoinsRepository.getCoinsData();
        }

        return result.observeOn(uiThread);
    }

//    @Override
//    public void saveSelectedCurrenciesList(List<String> currencies) {
//        Set<String> currenciesSet = new HashSet<>(currencies);
//        iCoinsRepository.saveSelectedCurrencies(currenciesSet);
//    }
//
//    @Override
//    public void saveSelectedCurrency(String name) {
//        Set<String> currenciesSet = iCoinsRepository.getSelectedCurrencies();
//        currenciesSet.add(name);
//        iCoinsRepository.saveSelectedCurrencies(currenciesSet);
//    }
//
//    @Override
//    public List<String> getSelectedCurrenciesList() {
//        Set<String> currenciesSet = iCoinsRepository.getSelectedCurrencies();
//        return new ArrayList<>(currenciesSet);
//    }
}
