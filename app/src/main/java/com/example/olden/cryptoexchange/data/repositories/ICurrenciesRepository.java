package com.example.olden.cryptoexchange.data.repositories;

import com.example.olden.cryptoexchange.data.network.models.response.CoinsData;
import com.example.olden.cryptoexchange.data.network.models.response.PricesData;

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
