package com.example.olden.cryptoexchange.presentation.currencies_list.di;

import com.example.olden.cryptoexchange.business.currencies_list.CurrenciesInteractor;
import com.example.olden.cryptoexchange.business.currencies_list.ICurrenciesInteractor;
import com.example.olden.cryptoexchange.data.repositories.cache.CurrenciesCache;
import com.example.olden.cryptoexchange.data.repositories.ICurrenciesRepository;
import com.example.olden.cryptoexchange.presentation.currencies_list.presenter.CurrenciesPresenter;
import com.example.olden.cryptoexchange.presentation.currencies_list.presenter.ICurrenciesPresenter;
import com.example.olden.cryptoexchange.presentation.currencies_list.view.ICurrenciesView;

import dagger.Module;
import dagger.Provides;

@Module
public class CurrenciesListModule {

    @Provides
    @CurrenciesListScope
    public ICurrenciesPresenter<ICurrenciesView> provideCurrenciesPresenter(ICurrenciesInteractor interactor) {
        return new CurrenciesPresenter(interactor);
    }

    @Provides
    @CurrenciesListScope
    public ICurrenciesInteractor provideCurrenciesInteractor(ICurrenciesRepository iCurrenciesRepository) {
        return new CurrenciesInteractor(iCurrenciesRepository);
    }
}
