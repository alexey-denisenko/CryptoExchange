package com.example.olden.cryptoexchange.di.component;


import com.example.olden.cryptoexchange.OkHttpInterceptorsModule;
import com.example.olden.cryptoexchange.di.module.AppModule;
import com.example.olden.cryptoexchange.di.module.CoinsListModule;
import com.example.olden.cryptoexchange.di.module.NetworkModule;
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

    CoinsListComponent plus(CoinsListModule module);

    PricesComponent plus(PricesModule module);
}
