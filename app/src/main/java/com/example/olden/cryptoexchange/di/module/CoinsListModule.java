package com.example.olden.cryptoexchange.di.module;

import com.example.olden.cryptoexchange.business.coins.CoinsInteractor;
import com.example.olden.cryptoexchange.business.coins.ICoinsInteractor;
import com.example.olden.cryptoexchange.data.repository.ICoinsRepository;
import com.example.olden.cryptoexchange.di.Qualifiers;
import com.example.olden.cryptoexchange.di.scope.CoinsListScope;
import com.example.olden.cryptoexchange.presentation.coins.presenter.CoinsPresenter;
import com.example.olden.cryptoexchange.presentation.coins.presenter.ICoinsPresenter;
import com.example.olden.cryptoexchange.presentation.coins.view.ICoinsView;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;

@Module
public class CoinsListModule {

    @Provides
    @CoinsListScope
    public ICoinsPresenter<ICoinsView> provideCurrenciesPresenter(ICoinsInteractor interactor) {
        return new CoinsPresenter(interactor);
    }

    @Provides
    @CoinsListScope
    public ICoinsInteractor provideCurrenciesInteractor(ICoinsRepository iCoinsRepository,
                                                        @Named(Qualifiers.UI_THREAD) Scheduler uiThread) {

        return new CoinsInteractor(iCoinsRepository, uiThread);
    }
}
