package com.example.olden.cryptoexchange.data.repositories;

import com.example.olden.cryptoexchange.data.network.models.response.CoinsData;
import com.example.olden.cryptoexchange.data.network.models.response.PricesData;

import java.util.List;

import io.reactivex.Single;

public interface ICurrenciesRepository {

    Single<CoinsData> getCoinsData();

    Single<PricesData> getPrices(String from, List<String> to);
}
