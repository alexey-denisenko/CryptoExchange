package com.example.olden.cryptoexchange.di.module;


import com.example.olden.cryptoexchange.business.prices.IPricesInteractor;
import com.example.olden.cryptoexchange.business.prices.PricesInteractor;
import com.example.olden.cryptoexchange.data.repository.ICoinsRepository;
import com.example.olden.cryptoexchange.di.scope.PricesScope;
import com.example.olden.cryptoexchange.presentation.prices.presenter.IPricesPresenter;
import com.example.olden.cryptoexchange.presentation.prices.presenter.PricesPresenter;
import com.example.olden.cryptoexchange.presentation.prices.view.IPricesView;

import dagger.Module;
import dagger.Provides;

@Module
public class PricesModule {

    @Provides
    @PricesScope
    IPricesPresenter<IPricesView> providePricesPresenter(IPricesInteractor interactor) {
        return new PricesPresenter(interactor);
    }

    @Provides
    @PricesScope
    IPricesInteractor providePricesInteractor(ICoinsRepository repository) {
        return new PricesInteractor(repository);
    }
}
