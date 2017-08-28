package com.example.olden.cryptoexchange;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.olden.cryptoexchange.business.currencies_list.CurrenciesListInteractor;
import com.example.olden.cryptoexchange.business.currencies_list.ICurrenciesListInteractor;
import com.example.olden.cryptoexchange.data.network.api.CryptoCompareService;
import com.example.olden.cryptoexchange.data.repositories.CurrenciesRepository;
import com.example.olden.cryptoexchange.data.repositories.ICurrenciesRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    @Provides @NonNull @Singleton
    public TypeAdapterFactory provideTypeAdapterFactory() {
        return ResponseTypeAdapterFactory.create();
    }

    @Provides @NonNull @Singleton
    public Gson provideGson(TypeAdapterFactory typeAdapterFactory) {
        return new GsonBuilder().
                registerTypeAdapterFactory(typeAdapterFactory)
                .create();
    }

    //TODO move this somewhere
    @Provides @Singleton
    public ICurrenciesRepository provideCurrenciesRepossitory(CryptoCompareService cryptoCompareService) {
        return new CurrenciesRepository(cryptoCompareService);
    }

    //TODO and this
    @Provides @Singleton
    public ICurrenciesListInteractor provideCurrenciesListInteractor(ICurrenciesRepository iCurrenciesRepository) {
        return new CurrenciesListInteractor(iCurrenciesRepository);
    }
}
