package com.example.olden.cryptoexchange.presentation.prices.presenter;

import com.example.olden.cryptoexchange.mvp.ICommonPresenter;
import com.example.olden.cryptoexchange.mvp.ICommonView;

public interface IPricesPresenter<V extends ICommonView> extends ICommonPresenter<V> {

    void subscribeOnPricesUpdate(String priceFrom);
}
