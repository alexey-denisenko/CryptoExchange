package com.example.olden.cryptoexchange.business.currencies_list;


import com.example.olden.cryptoexchange.data.repositories.ICurrenciesRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

public class CurrenciesInteractor implements ICurrenciesInteractor {

    private ICurrenciesRepository iCurrenciesRepository;

    public CurrenciesInteractor(ICurrenciesRepository iCurrenciesRepository) {
        this.iCurrenciesRepository = iCurrenciesRepository;
    }

    @Override
    public Single<List<String>> getCurrencyNamesList() {

        return iCurrenciesRepository.getCoinsData()
                .map(coinsData -> new ArrayList<>(coinsData.data().keySet()));
    }
}
