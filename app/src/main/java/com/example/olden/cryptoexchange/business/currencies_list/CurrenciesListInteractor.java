package com.example.olden.cryptoexchange.business.currencies_list;


import com.example.olden.cryptoexchange.data.network.models.response.CoinsData;
import com.example.olden.cryptoexchange.data.network.models.response.Currency;
import com.example.olden.cryptoexchange.data.repositories.ICurrenciesRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

public class CurrenciesListInteractor implements ICurrenciesListInteractor {

    private ICurrenciesRepository iCurrenciesRepository;

    private CoinsData coinsData;

    public CurrenciesListInteractor(ICurrenciesRepository iCurrenciesRepository) {
        this.iCurrenciesRepository = iCurrenciesRepository;
    }

    @Override
    public Single<List<String>> getCurrencyNamesList() {
        return iCurrenciesRepository.getCoinsData()
                .doOnSuccess(coinsData -> this.coinsData = coinsData)
                .map(coinsData -> new ArrayList<>(coinsData.data().keySet()));
    }

    @Override
    public Single<Currency> getCurrencyByName() {
        return null;
    }
}
