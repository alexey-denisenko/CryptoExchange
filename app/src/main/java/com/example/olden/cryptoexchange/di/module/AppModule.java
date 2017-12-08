package com.example.olden.cryptoexchange.di.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.olden.cryptoexchange.data.network.CryptoCompareApi;
import com.example.olden.cryptoexchange.data.repository.CurrenciesRepository;
import com.example.olden.cryptoexchange.data.repository.ICurrenciesRepository;
import com.example.olden.cryptoexchange.data.repository.cache.CurrenciesCache;
import com.example.olden.cryptoexchange.data.repository.cache.PricesCache;
import com.example.olden.cryptoexchange.other.keys.SharedPreferenceKey;
import com.example.olden.cryptoexchange.other.preferences.StringSetPreferenceType;
import com.example.olden.cryptoexchange.other.preferences.StringSetSetPreference;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class AppModule {

    private Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides @Singleton
    public ICurrenciesRepository provideCurrenciesRepossitory(CryptoCompareApi cryptoCompareApi,
                                                              StringSetPreferenceType stringSetPreferenceType,
                                                              CurrenciesCache currenciesCache,
                                                              PricesCache pricesCache) {
        return new CurrenciesRepository(cryptoCompareApi, stringSetPreferenceType, currenciesCache, pricesCache);
    }

    @Provides @Singleton
    public SharedPreferences provideSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides @Singleton
    public StringSetPreferenceType provideStringSetPreference(SharedPreferences sharedPreferences) {
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
