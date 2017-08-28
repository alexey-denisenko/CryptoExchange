package com.example.olden.cryptoexchange.presentation.currencies_list.presenter;

import com.example.olden.cryptoexchange.presentation.currencies_list.view.ICurrenciesView;

import java.util.List;

public interface ICurrenciesPresenter {

    void bindView(ICurrenciesView view);
    void unBindView();

    void fillAutoCompleteList();
    void saveCurrencyNamesToStorage(List<String> names);

    void showAddCurrencies();
    void addCurrencyItem(String name);
}
