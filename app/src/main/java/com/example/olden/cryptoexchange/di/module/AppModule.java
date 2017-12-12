package com.example.olden.cryptoexchange.di.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.olden.cryptoexchange.data.mapper.CoinsDataMapper;
import com.example.olden.cryptoexchange.data.repository.coins.CoinsRepository;
import com.example.olden.cryptoexchange.data.repository.coins.ICoinsRepository;
import com.example.olden.cryptoexchange.data.repository.coins.CoinsDataCache;
import com.example.olden.cryptoexchange.data.repository.prices.PricesCache;
import com.example.olden.cryptoexchange.data.repository.coins.datasource.data.CoinsDataStoreFactory;
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
    public ICoinsRepository provideCurrenciesRepossitory(StringSetPreferenceType stringSetPreferenceType,
                                                         CoinsDataStoreFactory coinsDataStoreFactory,
                                                         CoinsDataMapper coinsDataMapper) {

        return new CoinsRepository(stringSetPreferenceType, coinsDataStoreFactory, coinsDataMapper);
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
    public CoinsDataCache provideCurrenciesCache() {
        return new CoinsDataCache();
    }

    @Provides @Singleton
    public PricesCache providePricesCache() {
        return new PricesCache();
    }
}
