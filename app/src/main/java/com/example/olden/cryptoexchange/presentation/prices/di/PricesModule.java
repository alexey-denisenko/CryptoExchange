package com.example.olden.cryptoexchange.presentation.prices.di;


import com.example.olden.cryptoexchange.business.prices.IPricesInteractor;
import com.example.olden.cryptoexchange.business.prices.PricesInteractor;
import com.example.olden.cryptoexchange.data.repositories.ICurrenciesRepository;
import com.example.olden.cryptoexchange.presentation.prices.presenter.IPricesPresenter;
import com.example.olden.cryptoexchange.presentation.prices.presenter.PricesPresenter;
import com.example.olden.cryptoexchange.presentation.prices.view.IPricesView;

import dagger.Module;
import dagger.Provides;

@Module
public class PricesModule {

    @Provides
    @PricesScope
    IPricesInteractor providePricesInteractor(ICurrenciesRepository repository) {
        return new PricesInteractor(repository);
    }

    @Provides
    @PricesScope
    IPricesPresenter<IPricesView> providePricesPresenter(IPricesInteractor interactor) {
        return new PricesPresenter(interactor);
    }
}
