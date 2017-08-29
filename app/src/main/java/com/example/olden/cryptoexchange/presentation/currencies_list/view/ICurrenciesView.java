package com.example.olden.cryptoexchange.presentation.currencies_list.view;

import java.util.List;

public interface ICurrenciesView {

    void setAutoCompleteTextView(List<String> currencies);

    void showSavedCurrenciesList(List<String> currencies);

    void showSearchView();

    void setFocusOnSearchView();

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
