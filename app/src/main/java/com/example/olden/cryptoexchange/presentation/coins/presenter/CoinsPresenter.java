package com.example.olden.cryptoexchange.presentation.coins.presenter;


import com.example.olden.cryptoexchange.BuildConfig;
import com.example.olden.cryptoexchange.business.coins.ICoinsInteractor;
import com.example.olden.cryptoexchange.di.scope.CoinsListScope;
import com.example.olden.cryptoexchange.mvp.BasePresenter;
import com.example.olden.cryptoexchange.presentation.coins.view.ICoinsView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

@CoinsListScope
public class CoinsPresenter extends BasePresenter<ICoinsView> implements ICoinsPresenter<ICoinsView> {

    private ICoinsInteractor interactor;

    @Inject
    public CoinsPresenter(ICoinsInteractor iCoinsInteractor) {
        this.interactor = iCoinsInteractor;
    }

    @Override
    public void bindView(ICoinsView view) {
        super.bindView(view);

        Disposable disposable = interactor.getSelectedCoinsList()
                .subscribe(this::handleSuccessLoadSelectedCoins,
                        this::handleErrorLoadSelectedCoins);

        compositeDisposable.add(disposable);
    }

    @Override
    public void fillAutoCompleteList(boolean forceUpdate) {
        getViewOrThrow().showLoading();
        getViewOrThrow().disableAddButton();

        Disposable disposable = interactor.getCoinNamesList(forceUpdate)
                .subscribe(this::handleSuccessLoadCoinsList,
                        this::handleErrorLoadCoinsList);

        compositeDisposable.add(disposable);
    }

    @Override
    public void saveSelectedCoins(List<String> names) {
        interactor.saveSelectedCoins(names);
    }

    @Override
    public void showCoinsSearch() {
        getViewOrThrow().showSearchView();
        getViewOrThrow().setFocusOnSearchView();
    }

    @Override
    public void addSelectedCoin(String name) {
        getViewOrThrow().cleanSearchView();
        getViewOrThrow().hideSearchView();
        getViewOrThrow().hideKeyboard();
        getViewOrThrow().showSelectedCoin(name);
        getViewOrThrow().removeCoinFromSearch(name);

        interactor.saveSelectedCoins(name);
    }

    private void handleSuccessLoadCoinsList(List<String> coins) {

        setCurrenciesListToView(coins);
    }

    private void handleErrorLoadCoinsList(Throwable throwable) {
        getViewOrThrow().hideLoading();
        getViewOrThrow().showErrorLoading();

        if (BuildConfig.DEBUG) {
            throwable.printStackTrace();
        }
    }

    private void setCurrenciesListToView(List<String> strings) {
        getViewOrThrow().hideLoading();
        getViewOrThrow().enableAddButton();
        getViewOrThrow().setAutoCompleteTextView(strings);
    }

    private void handleSuccessLoadSelectedCoins(List<String> selectedCoins) {
        getViewOrThrow().showSelectedCoins(selectedCoins);
    }

    private void handleErrorLoadSelectedCoins(Throwable throwable) {
        //no-op
    }
}
