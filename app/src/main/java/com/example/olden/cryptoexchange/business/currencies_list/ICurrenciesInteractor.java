package com.example.olden.cryptoexchange.business.currencies_list;


import java.util.List;

import io.reactivex.Single;

public interface ICurrenciesInteractor {

    Single<List<String>> getCurrencyNamesList();

    void saveSelectedCurrenciesList(List<String> currencies);

    List<String> getSelectedCurrenciesList();
}
