package com.example.olden.cryptoexchange.presentation.prices.presenter;

import com.example.olden.cryptoexchange.common.ICommonPresenter;

public interface IPricesPresenter<V> extends ICommonPresenter<V> {

    void subscribeOnPricesUpdate(String priceFrom);
}
