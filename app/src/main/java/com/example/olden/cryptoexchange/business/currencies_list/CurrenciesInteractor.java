package com.example.olden.cryptoexchange.business.currencies_list;


import com.example.olden.cryptoexchange.data.repositories.ICurrenciesRepository;
import com.example.olden.cryptoexchange.di.scope.CurrenciesListScope;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import io.reactivex.Single;

@CurrenciesListScope
public class CurrenciesInteractor implements ICurrenciesInteractor {

    private ICurrenciesRepository iCurrenciesRepository;

    @Inject
    public CurrenciesInteractor(ICurrenciesRepository iCurrenciesRepository) {
        this.iCurrenciesRepository = iCurrenciesRepository;
    }

    @Override
    public Single<List<String>> getCurrencyNamesList(boolean forceRefresh) {

        if(forceRefresh) {
            iCurrenciesRepository.refreshCurrencies();
        }

        return iCurrenciesRepository.getCoinsData()
                .map(coinsData -> new ArrayList<>(coinsData.data().keySet()));
    }

    @Override
    public void saveSelectedCurrenciesList(List<String> currencies) {
        Set<String> currenciesSet = new HashSet<>(currencies);
        iCurrenciesRepository.saveSelectedCurrencies(currenciesSet);
    }

    @Override
    public void saveSelectedCurrency(String name) {
        Set<String> currenciesSet = iCurrenciesRepository.getSelectedCurrencies();
        currenciesSet.add(name);
        iCurrenciesRepository.saveSelectedCurrencies(currenciesSet);
    }

    @Override
    public List<String> getSelectedCurrenciesList() {
        Set<String> currenciesSet = iCurrenciesRepository.getSelectedCurrencies();
        return new ArrayList<>(currenciesSet);
    }
}
