package com.example.olden.cryptoexchange.presentation.currencies_list.view;

import java.util.List;

public interface ICurrenciesView {

    void setAutoCompleteTextView(List<String> currencies);

    void showSavedCurrenciesList(List<String> currencies);
}
