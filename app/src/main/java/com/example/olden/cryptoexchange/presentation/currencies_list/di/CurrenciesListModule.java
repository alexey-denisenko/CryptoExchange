package com.example.olden.cryptoexchange.presentation.currencies_list.di;

import com.example.olden.cryptoexchange.business.currencies_list.CurrenciesInteractor;
import com.example.olden.cryptoexchange.business.currencies_list.ICurrenciesInteractor;
import com.example.olden.cryptoexchange.data.repositories.ICurrenciesRepository;
import com.example.olden.cryptoexchange.presentation.currencies_list.presenter.CurrenciesPresenter;
import com.example.olden.cryptoexchange.presentation.currencies_list.presenter.CurrenciesPresenterCache;
import com.example.olden.cryptoexchange.presentation.currencies_list.presenter.ICurrenciesPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class CurrenciesListModule {

    @Provides
    @CurrenciesListScope
    public ICurrenciesPresenter provideCurrenciesPresenter(ICurrenciesInteractor interactor, CurrenciesPresenterCache cache) {
        return new CurrenciesPresenter(interactor, cache);
    }

    @Provides
    @CurrenciesListScope
    public ICurrenciesInteractor provideCurrenciesInteractor(ICurrenciesRepository iCurrenciesRepository) {
        return new CurrenciesInteractor(iCurrenciesRepository);
    }

    @Provides
    @CurrenciesListScope
    public CurrenciesPresenterCache provideCurrenciesPresenterCache() {
        return new CurrenciesPresenterCache();
    }
}
