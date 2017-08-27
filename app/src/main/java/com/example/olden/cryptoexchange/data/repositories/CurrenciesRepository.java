package com.example.olden.cryptoexchange.data.repositories;


import com.example.olden.cryptoexchange.data.network.api.CryptoCompareService;
import com.example.olden.cryptoexchange.data.network.models.response.CoinsData;
import com.example.olden.cryptoexchange.data.network.models.response.PricesData;

import java.util.List;

import io.reactivex.Single;

public class CurrenciesRepository implements ICurrenciesRepository{

    private CryptoCompareService cryptoCompareService;

    public CurrenciesRepository(CryptoCompareService cryptoCompareService) {

        this.cryptoCompareService = cryptoCompareService;
    }

    @Override
    public Single<CoinsData> getCoinsData() {
        return cryptoCompareService.getCoinsData();
    }

    @Override
    public Single<PricesData> getPrices(String from, List<String> to) {
        return cryptoCompareService.getPrices(from, to);
    }
}
