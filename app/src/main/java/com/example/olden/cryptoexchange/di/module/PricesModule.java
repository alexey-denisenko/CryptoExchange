package com.example.olden.cryptoexchange.di.module;


import com.example.olden.cryptoexchange.business.prices.IPricesInteractor;
import com.example.olden.cryptoexchange.business.prices.PricesInteractor;
import com.example.olden.cryptoexchange.data.repository.ICoinsRepository;
import com.example.olden.cryptoexchange.di.Qualifiers;
import com.example.olden.cryptoexchange.di.scope.PricesScope;
import com.example.olden.cryptoexchange.presentation.prices.presenter.IPricesPresenter;
import com.example.olden.cryptoexchange.presentation.prices.presenter.PricesPresenter;
import com.example.olden.cryptoexchange.presentation.prices.view.IPricesView;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;

@Module
public class PricesModule {

    @Provides
    @PricesScope
    IPricesPresenter<IPricesView> providePricesPresenter(IPricesInteractor interactor) {
        return new PricesPresenter(interactor);
    }

    @Provides
    @PricesScope
    IPricesInteractor providePricesInteractor(ICoinsRepository repository,
                                              @Named(Qualifiers.UI_THREAD) Scheduler uiThread) {

        return new PricesInteractor(repository, uiThread);
    }
}
