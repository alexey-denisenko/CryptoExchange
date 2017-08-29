package com.example.olden.cryptoexchange.presentation.currencies_list.presenter;


import com.example.olden.cryptoexchange.business.currencies_list.ICurrenciesInteractor;
import com.example.olden.cryptoexchange.presentation.currencies_list.view.ICurrenciesView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CurrenciesPresenter implements ICurrenciesPresenter<ICurrenciesView> {

    private static final String TAG = "CurrenciesPresenter";

    private ICurrenciesView view;

    private ICurrenciesInteractor interactor;
    private CurrenciesPresenterCache currenciesPresenterCache;

    private CompositeDisposable compositeSubscription = new CompositeDisposable();

    public CurrenciesPresenter(ICurrenciesInteractor iCurrenciesInteractor,
                               CurrenciesPresenterCache currenciesPresenterCache) {
        this.interactor = iCurrenciesInteractor;
        this.currenciesPresenterCache = currenciesPresenterCache;
    }

    @Override
    public void bindView(ICurrenciesView view) {
        this.view = view;
        List<String> currencies = interactor.getSelectedCurrenciesList();
        this.view.showSavedCurrenciesList(currencies);
    }

    @Override
    public void unBindView() {
        compositeSubscription.clear();
        this.view = null;
    }

    @Override
    public void fillAutoCompleteList() {
        if (currenciesPresenterCache.isCurrenciesListCached()) {
            setCurrenciesListToView(currenciesPresenterCache.getCurrenciesList());
        } else {
            loadCurrenciesListFromData();
        }
    }

    @Override
    public void saveCurrencyNamesToStorage(List<String> names) {
        interactor.saveSelectedCurrenciesList(names);
    }

    @Override
    public void showAddCurrencies() {
        view.showSearchView();
        view.setFocusOnSearchView();
    }

    @Override
    public void addCurrencyItem(String name) {
        view.cleanSearchView();
        view.hideSearchView();
        view.showNewCurrencyItem(name);
        view.removeCurrencyFromSearch(name);
    }

    private void loadCurrenciesListFromData() {
        view.showLoading();
        view.disableAddButton();

        Disposable disposable = interactor.getCurrencyNamesList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleSuccessLoadCurrenciesList,
                        this::handleErrorLoadCurrenciesList);
        compositeSubscription.add(disposable);
    }


    private void handleSuccessLoadCurrenciesList(List<String> strings) {

        currenciesPresenterCache.setCurrenciesList(strings);
        setCurrenciesListToView(strings);
    }

    private void setCurrenciesListToView(List<String> strings) {
        view.hideLoading();
        view.enableAddButton();

        List<String> currencies = interactor.getSelectedCurrenciesList();
        strings.removeAll(currencies);
        view.setAutoCompleteTextView(strings);
    }

    private void handleErrorLoadCurrenciesList(Throwable throwable) {
        view.hideLoading();
        view.showErrorLoading();
    }
}
