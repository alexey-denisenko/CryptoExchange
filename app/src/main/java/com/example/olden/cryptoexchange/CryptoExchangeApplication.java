package com.example.olden.cryptoexchange;


import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.olden.cryptoexchange.di.component.AppComponent;
import com.example.olden.cryptoexchange.di.component.DaggerAppComponent;
import com.example.olden.cryptoexchange.di.module.AppModule;
import com.example.olden.cryptoexchange.di.module.NetworkModule;

import timber.log.Timber;

public class CryptoExchangeApplication extends Application {

    public static final String BASE_URL = "https://min-api.cryptocompare.com/data/";
    private AppComponent appComponent;

    @NonNull
    public static CryptoExchangeApplication appComponent(@NonNull Context context) {
        return (CryptoExchangeApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if(BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        appComponent = prepareApplicationComponent().build();

    }

    protected DaggerAppComponent.Builder prepareApplicationComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule(BASE_URL));
    }

    @NonNull
    public AppComponent appComponent() {return appComponent;}
}
