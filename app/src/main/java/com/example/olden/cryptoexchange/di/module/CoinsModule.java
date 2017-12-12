package com.example.olden.cryptoexchange.di.module;

import com.example.olden.cryptoexchange.business.currencies_list.CurrenciesInteractor;
import com.example.olden.cryptoexchange.business.currencies_list.ICurrenciesInteractor;
import com.example.olden.cryptoexchange.data.repository.coins.ICoinsRepository;
import com.example.olden.cryptoexchange.di.Qualifiers;
import com.example.olden.cryptoexchange.di.scope.CurrenciesListScope;
import com.example.olden.cryptoexchange.presentation.currencies_list.presenter.CurrenciesPresenter;
import com.example.olden.cryptoexchange.presentation.currencies_list.presenter.ICurrenciesPresenter;
import com.example.olden.cryptoexchange.presentation.currencies_list.view.ICurrenciesView;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;

@Module
public class CoinsModule {

    @Provides
    @CurrenciesListScope
    public ICurrenciesPresenter<ICurrenciesView> provideCurrenciesPresenter(ICurrenciesInteractor interactor) {
        return new CurrenciesPresenter(interactor);
    }

    @Provides
    @CurrenciesListScope
    public ICurrenciesInteractor provideCurrenciesInteractor(ICoinsRepository iCoinsRepository,
                                                             @Named(Qualifiers.UI_THREAD) Scheduler uiThread) {

        return new CurrenciesInteractor(iCoinsRepository, uiThread);
    }
}
