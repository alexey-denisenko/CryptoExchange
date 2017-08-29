package com.example.olden.cryptoexchange.presentation.currencies_list.presenter;

import com.example.olden.cryptoexchange.common.ICommonPresenter;

import java.util.List;

public interface ICurrenciesPresenter<V> extends ICommonPresenter<V> {

    void fillAutoCompleteList();
    void saveCurrencyNamesToStorage(List<String> names);

    void showAddCurrencies();
    void addCurrencyItem(String name);
}
