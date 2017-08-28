package com.example.olden.cryptoexchange.presentation.currencies_list.presenter;


import java.util.List;

public class CurrenciesPresenterCache {

    private List<String> currenciesList;

    public List<String> getCurrenciesList() {
        return currenciesList;
    }

    public void setCurrenciesList(List<String> currenciesList) {
        this.currenciesList = currenciesList;
    }

    public boolean isCurrenciesListCached() {
        return currenciesList != null;
    }
}
