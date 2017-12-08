package com.example.olden.cryptoexchange.presentation.currencies_list.presenter;


import com.example.olden.cryptoexchange.business.currencies_list.ICurrenciesInteractor;
import com.example.olden.cryptoexchange.common.mvp.BasePresenter;
import com.example.olden.cryptoexchange.presentation.currencies_list.di.CurrenciesListScope;
import com.example.olden.cryptoexchange.presentation.currencies_list.view.ICurrenciesView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@CurrenciesListScope
public class CurrenciesPresenter extends BasePresenter<ICurrenciesView> implements ICurrenciesPresenter<ICurrenciesView> {

    private ICurrenciesInteractor interactor;

    @Inject
    public CurrenciesPresenter(ICurrenciesInteractor iCurrenciesInteractor) {
        this.interactor = iCurrenciesInteractor;
    }

    @Override
    public void bindView(ICurrenciesView view) {
        super.bindView(view);
        List<String> currencies = interactor.getSelectedCurrenciesList();
        getViewOrThrow().showSavedCurrenciesList(currencies);
    }

    @Override
    public void fillAutoCompleteList(boolean forceUpdate) {
        loadCurrenciesListFromData(forceUpdate);
    }

    @Override
    public void saveCurrencyNamesToStorage(List<String> names) {
        interactor.saveSelectedCurrenciesList(names);
    }

    @Override
    public void showAddCurrencies() {
        getViewOrThrow().showSearchView();
        getViewOrThrow().setFocusOnSearchView();
    }

    @Override
    public void addCurrencyItem(String name) {
        getViewOrThrow().cleanSearchView();
        getViewOrThrow().hideSearchView();
        getViewOrThrow().hideKeyboard();
        getViewOrThrow().showNewCurrencyItem(name);
        getViewOrThrow().removeCurrencyFromSearch(name);

        saveNewlySelectedCurrency(name);
    }

    private void loadCurrenciesListFromData(boolean forceRefresh) {
        getViewOrThrow().showLoading();
        getViewOrThrow().disableAddButton();

        Disposable disposable = interactor.getCurrencyNamesList(forceRefresh)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleSuccessLoadCurrenciesList,
                        this::handleErrorLoadCurrenciesList);
        compositeDisposable.add(disposable);
    }


    private void handleSuccessLoadCurrenciesList(List<String> strings) {

        setCurrenciesListToView(strings);
    }

    private void setCurrenciesListToView(List<String> strings) {
        getViewOrThrow().hideLoading();
        getViewOrThrow().enableAddButton();

        List<String> currencies = interactor.getSelectedCurrenciesList();
        strings.removeAll(currencies);
        getViewOrThrow().setAutoCompleteTextView(strings);
    }

    private void handleErrorLoadCurrenciesList(Throwable throwable) {
        getViewOrThrow().hideLoading();
        getViewOrThrow().showErrorLoading();
    }

    private void saveNewlySelectedCurrency(String name) {
        interactor.saveSelectedCurrency(name);
    }
}
