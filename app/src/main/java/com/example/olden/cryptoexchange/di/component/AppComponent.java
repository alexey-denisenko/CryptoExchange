package com.example.olden.cryptoexchange.di.component;


import com.example.olden.cryptoexchange.OkHttpInterceptorsModule;
import com.example.olden.cryptoexchange.di.module.AppModule;
import com.example.olden.cryptoexchange.di.module.NetworkModule;
import com.example.olden.cryptoexchange.di.module.CoinsModule;
import com.example.olden.cryptoexchange.di.module.PricesModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        NetworkModule.class,
        OkHttpInterceptorsModule.class
})
public interface AppComponent {

    CurrenciesListComponent plus(CoinsModule module);

    PricesComponent plus(PricesModule module);
}
