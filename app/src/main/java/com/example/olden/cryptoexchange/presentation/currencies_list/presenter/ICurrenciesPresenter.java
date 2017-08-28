package com.example.olden.cryptoexchange.presentation.currencies_list.presenter;

import com.example.olden.cryptoexchange.presentation.currencies_list.view.ICurrenciesView;

public interface ICurrenciesPresenter {

    void bindView(ICurrenciesView view);
    void unBindView();

    void fillAutoCompleteList();
}
