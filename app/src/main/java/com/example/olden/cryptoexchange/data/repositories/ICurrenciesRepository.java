package com.example.olden.cryptoexchange.data.repositories;

import com.example.olden.cryptoexchange.data.network.models.response.CoinsData;
import com.example.olden.cryptoexchange.data.network.models.response.PricesData;

import java.util.List;
import java.util.Set;

import io.reactivex.Single;

public interface ICurrenciesRepository {

    Single<CoinsData> getCoinsData();

    Single<PricesData> getPrices(String from, List<String> to);

    void saveSelectedCurrencies(Set<String> currencies);

    Set<String> getSelectedCurrencies();
}
