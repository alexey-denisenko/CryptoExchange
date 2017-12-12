package com.example.olden.cryptoexchange.presentation.coins.view;

import com.example.olden.cryptoexchange.mvp.ICommonView;

import java.util.List;

public interface ICoinsView extends ICommonView {

    void setAutoCompleteTextView(List<String> coins);

    void showSelectedCoins(List<String> coins);

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

    void showSelectedCoin(String name);

    void removeCoinFromSearch(String name);
}
