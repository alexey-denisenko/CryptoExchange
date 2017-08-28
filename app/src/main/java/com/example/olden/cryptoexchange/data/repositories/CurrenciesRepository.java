package com.example.olden.cryptoexchange.data.repositories;


import com.example.olden.cryptoexchange.data.network.api.CryptoCompareService;
import com.example.olden.cryptoexchange.data.network.models.response.CoinsData;
import com.example.olden.cryptoexchange.data.network.models.response.PricesData;
import com.example.olden.cryptoexchange.other.preferences.StringSetPreferenceType;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.reactivex.Single;

public class CurrenciesRepository implements ICurrenciesRepository{

    private CryptoCompareService cryptoCompareService;

    private StringSetPreferenceType stringSetPreferenceType;

    public CurrenciesRepository(CryptoCompareService cryptoCompareService, StringSetPreferenceType stringSetPreferenceType) {

        this.cryptoCompareService = cryptoCompareService;
        this.stringSetPreferenceType = stringSetPreferenceType;
    }

    @Override
    public Single<CoinsData> getCoinsData() {
        return cryptoCompareService.getCoinsData();
    }

    @Override
    public Single<PricesData> getPrices(String from, List<String> to) {
        return cryptoCompareService.getPrices(from, to);
    }

    @Override
    public void saveSelectedCurrencies(Set<String> currencies) {
        stringSetPreferenceType.set(currencies);
    }

    @Override
    public Set<String> getSelectedCurrencies() {

        if(stringSetPreferenceType.isSet()) {
            return stringSetPreferenceType.get();
        }

        return new HashSet<>();
    }
}
