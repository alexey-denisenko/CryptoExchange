package com.example.olden.cryptoexchange.presentation.prices.presenter;


import android.util.Log;

import com.example.olden.cryptoexchange.business.prices.IPricesInteractor;
import com.example.olden.cryptoexchange.data.network.models.response.Price;
import com.example.olden.cryptoexchange.presentation.prices.view.IPricesView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class PricesPresenter implements IPricesPresenter<IPricesView> {

    private static final String TAG = "PricesPresenter";

    private PricesPresenterCache cache;
    private IPricesInteractor interactor;
    private IPricesView view;

    private final List<String> toPrices = new ArrayList<String>() {{
        add("USD");
        add("EUR");
    }};

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public PricesPresenter(PricesPresenterCache cache, IPricesInteractor interactor) {
        this.cache = cache;
        this.interactor = interactor;
    }

    @Override
    public void bindView(IPricesView view) {
        this.view = view;
    }

    @Override
    public void unBindView() {
        compositeDisposable.clear();
        this.view = null;
    }

    @Override
    public void subscribeOnPricesUpdate(String fromPrice) {

        view.showLoading();
        if (cache.isCacheExists()) {
            view.hideLoading();
            view.showNewPrices(cache.getPrices());
        }
        interactor.getUpdatablePrices(fromPrice, toPrices)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(pricesData -> handleSuccessLoadPrices(pricesData.data()),
                        this::handleErrorLoadPrices);
    }

    private void handleSuccessLoadPrices(List<Price> prices) {

        view.hideLoading();
        if(prices.isEmpty()) {
            view.showNoDataLoaded();
        } else {
            cache.setPrices(prices);
            view.showNewPrices(prices);
        }
    }

    private void handleErrorLoadPrices(Throwable throwable) {
        //Todo show error
        Log.e(TAG, "handleErrorLoadPrices: ", throwable);
    }
}
