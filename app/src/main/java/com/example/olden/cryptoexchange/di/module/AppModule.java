package com.example.olden.cryptoexchange.di.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.olden.cryptoexchange.data.mapper.CoinsDataMapper;
import com.example.olden.cryptoexchange.data.repository.CoinsRepository;
import com.example.olden.cryptoexchange.data.repository.ICoinsRepository;
import com.example.olden.cryptoexchange.data.repository.cache.CoinPricesCache;
import com.example.olden.cryptoexchange.data.repository.cache.CoinsDataCache;
import com.example.olden.cryptoexchange.data.repository.datasource.coins.CoinsDataStoreFactory;
import com.example.olden.cryptoexchange.data.repository.datasource.price.CoinPricesStoreFactory;
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
    ICoinsRepository provideCurrenciesRepossitory(CoinsDataStoreFactory coinsDataStoreFactory,
                                                  StringSetPreferenceType stringSetPreferenceType,
                                                  CoinsDataMapper coinsDataMapper,
                                                  CoinPricesStoreFactory coinPricesStoreFactory) {

        return new CoinsRepository(coinsDataStoreFactory, coinPricesStoreFactory, stringSetPreferenceType, coinsDataMapper);
    }

    @Provides @Singleton
    SharedPreferences provideSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides @Singleton
    StringSetPreferenceType provideStringSetPreference(SharedPreferences sharedPreferences) {
        return new StringSetSetPreference(sharedPreferences, SharedPreferenceKey.SAVED_CURRENCIES);
    }

    @Provides @Singleton
    CoinsDataCache provideCurrenciesCache() {
        return new CoinsDataCache();
    }

    @Provides @Singleton
    CoinPricesCache providePricesCache() {
        return new CoinPricesCache();
    }
}
