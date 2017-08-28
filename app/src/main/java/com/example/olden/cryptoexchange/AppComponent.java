package com.example.olden.cryptoexchange;


import com.example.olden.cryptoexchange.data.network.NetworkModule;
import com.example.olden.cryptoexchange.data.network.api.ApiModule;
import com.example.olden.cryptoexchange.presentation.currencies_list.di.CurrenciesListComponent;
import com.example.olden.cryptoexchange.presentation.currencies_list.di.CurrenciesListModule;

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
}
