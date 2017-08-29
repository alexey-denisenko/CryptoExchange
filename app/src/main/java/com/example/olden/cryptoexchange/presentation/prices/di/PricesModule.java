package com.example.olden.cryptoexchange.presentation.prices.di;


import com.example.olden.cryptoexchange.business.prices.IPricesInteractor;
import com.example.olden.cryptoexchange.business.prices.PricesInteractor;
import com.example.olden.cryptoexchange.data.repositories.ICurrenciesRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PricesModule {

    @Provides
    @Singleton
    IPricesInteractor providePricesInteractor(ICurrenciesRepository repository) {
        return new PricesInteractor(repository);
    }
}
