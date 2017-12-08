package com.example.olden.cryptoexchange.presentation.prices.view;

import com.example.olden.cryptoexchange.mvp.ICommonView;
import com.example.olden.cryptoexchange.data.entity.Price;

import java.util.List;

public interface IPricesView extends ICommonView {

    void showLoading();

    void hideLoading();

    void showNoDataLoaded();

    void showNewPrices(List<Price> prices);
}
