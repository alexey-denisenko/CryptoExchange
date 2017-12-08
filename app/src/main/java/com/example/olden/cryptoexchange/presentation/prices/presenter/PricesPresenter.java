package com.example.olden.cryptoexchange.presentation.prices.presenter;


import com.example.olden.cryptoexchange.business.prices.IPricesInteractor;
import com.example.olden.cryptoexchange.mvp.BasePresenter;
import com.example.olden.cryptoexchange.data.entity.Price;
import com.example.olden.cryptoexchange.di.scope.PricesScope;
import com.example.olden.cryptoexchange.presentation.prices.view.IPricesView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@PricesScope
public class PricesPresenter extends BasePresenter<IPricesView> implements IPricesPresenter<IPricesView> {

    private IPricesInteractor interactor;

    private final List<String> toPrices = new ArrayList<String>() {{
        add("USD");
        add("EUR");
    }};

    @Inject
    public PricesPresenter(IPricesInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void subscribeOnPricesUpdate(String fromPrice) {

        getViewOrThrow().showLoading();
        loadPricesFromData(fromPrice);
    }

    private void loadPricesFromData(String fromPrice) {
        Disposable disposable = interactor.getUpdatablePrices(fromPrice, toPrices)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(pricesData -> handleSuccessLoadPrices(pricesData.data()),
                        this::handleErrorLoadPrices);
        compositeDisposable.add(disposable);
    }

    private void handleSuccessLoadPrices(List<Price> prices) {

        getViewOrThrow().hideLoading();
        if (prices.isEmpty()) {
            getViewOrThrow().showNoDataLoaded();
        } else {
            getViewOrThrow().showNewPrices(prices);
        }

    }

    private void handleErrorLoadPrices(Throwable throwable) {
        getViewOrThrow().showNoDataLoaded();
    }
}
