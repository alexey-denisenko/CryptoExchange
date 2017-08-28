package com.example.olden.cryptoexchange.business.currencies_list;


import com.example.olden.cryptoexchange.data.network.models.response.Currency;

import java.util.List;

import io.reactivex.Single;

public interface ICurrenciesListInteractor {

    Single<List<String>> getCurrencyNamesList();

    Single<Currency> getCurrencyByName();

}
