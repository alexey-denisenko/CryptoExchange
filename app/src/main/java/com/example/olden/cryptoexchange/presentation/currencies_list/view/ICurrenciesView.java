package com.example.olden.cryptoexchange.presentation.currencies_list.view;

import com.example.olden.cryptoexchange.mvp.ICommonView;

import java.util.List;

public interface ICurrenciesView extends ICommonView {

    void setAutoCompleteTextView(List<String> currencies);

    void showSavedCurrenciesList(List<String> currencies);

    void showSearchView();

    void setFocusOnSearchView();

    void hideKeyboard();

    void hideSearchView();

    void cleanSearchView();

    void showLoading();

    void hideLoading();

    void enableAddButton();

    void disableAddButton();

    void showErrorLoading();

    void showNewCurrencyItem(String name);

    void removeCurrencyFromSearch(String name);
}
