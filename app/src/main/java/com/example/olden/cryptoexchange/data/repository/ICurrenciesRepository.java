package com.example.olden.cryptoexchange.data.repository;

import com.example.olden.cryptoexchange.data.entity.CoinsData;
import com.example.olden.cryptoexchange.data.entity.PricesData;

import java.util.List;
import java.util.Set;

import io.reactivex.Single;

public interface ICurrenciesRepository {

    int UPDATE_PERIOD = 5;

    int INITIAL_DELAY = 0;

    Single<CoinsData> getCoinsData();

    Single<PricesData> getPrices(String from, List<String> to);

    void saveSelectedCurrencies(Set<String> currencies);

    Set<String> getSelectedCurrencies();

    void refreshCurrencies();
}
