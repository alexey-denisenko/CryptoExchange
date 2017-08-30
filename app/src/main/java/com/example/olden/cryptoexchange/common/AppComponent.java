package com.example.olden.cryptoexchange.common;


import com.example.olden.cryptoexchange.OkHttpInterceptorsModule;
import com.example.olden.cryptoexchange.data.network.NetworkModule;
import com.example.olden.cryptoexchange.data.network.api.ApiModule;
import com.example.olden.cryptoexchange.presentation.currencies_list.di.CurrenciesListComponent;
import com.example.olden.cryptoexchange.presentation.currencies_list.di.CurrenciesListModule;
import com.example.olden.cryptoexchange.presentation.prices.di.PricesComponent;
import com.example.olden.cryptoexchange.presentation.prices.di.PricesModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        ApiModule.class,
        NetworkModule.class,
        OkHttpInterceptorsModule.class
})
public interface AppComponent {

    CurrenciesListComponent plus(CurrenciesListModule module);

    PricesComponent plus(PricesModule module);
}
