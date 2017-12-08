package com.example.olden.cryptoexchange.presentation.currencies_list.presenter;

import com.example.olden.cryptoexchange.mvp.ICommonPresenter;
import com.example.olden.cryptoexchange.mvp.ICommonView;

import java.util.List;

public interface ICurrenciesPresenter<V extends ICommonView> extends ICommonPresenter<V> {

    void fillAutoCompleteList(boolean forceUpdate);
    void saveCurrencyNamesToStorage(List<String> names);

    void showAddCurrencies();
    void addCurrencyItem(String name);
}
