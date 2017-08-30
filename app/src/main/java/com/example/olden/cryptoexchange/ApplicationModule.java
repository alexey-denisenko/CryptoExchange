package com.example.olden.cryptoexchange;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import com.example.olden.cryptoexchange.data.network.api.CryptoCompareService;
import com.example.olden.cryptoexchange.data.repositories.CurrenciesRepository;
import com.example.olden.cryptoexchange.data.repositories.ICurrenciesRepository;
import com.example.olden.cryptoexchange.data.repositories.cache.CurrenciesCache;
import com.example.olden.cryptoexchange.data.repositories.cache.PricesCache;
import com.example.olden.cryptoexchange.other.ResponseTypeAdapterFactory;
import com.example.olden.cryptoexchange.other.keys.SharedPreferenceKey;
import com.example.olden.cryptoexchange.other.preferences.StringSetSetPreference;
import com.example.olden.cryptoexchange.other.preferences.StringSetPreferenceType;
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

    @Provides @Singleton
    public ICurrenciesRepository provideCurrenciesRepossitory(CryptoCompareService cryptoCompareService,
                                                              StringSetPreferenceType stringSetPreferenceType,
                                                              CurrenciesCache currenciesCache,
                                                              PricesCache pricesCache) {
        return new CurrenciesRepository(cryptoCompareService, stringSetPreferenceType, currenciesCache, pricesCache);
    }

    @Provides @Singleton
    public SharedPreferences provideSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides @Singleton
    public StringSetPreferenceType provideStringPreference(SharedPreferences sharedPreferences) {
        return new StringSetSetPreference(sharedPreferences, SharedPreferenceKey.SAVED_CURRENCIES);
    }

    @Provides @Singleton
    public CurrenciesCache provideCurrenciesCache() {
        return new CurrenciesCache();
    }

    @Provides @Singleton
    public PricesCache providePricesCache() {
        return new PricesCache();
    }
}
