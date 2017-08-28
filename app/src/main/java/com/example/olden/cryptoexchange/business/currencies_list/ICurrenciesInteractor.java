package com.example.olden.cryptoexchange.business.currencies_list;


import com.example.olden.cryptoexchange.data.network.models.response.Currency;

import java.util.List;

import io.reactivex.Single;

public interface ICurrenciesInteractor {

    Single<List<String>> getCurrencyNamesList();

}
