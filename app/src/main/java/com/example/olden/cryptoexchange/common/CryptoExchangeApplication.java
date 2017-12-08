package com.example.olden.cryptoexchange.common;


import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.olden.cryptoexchange.common.di.AppComponent;
import com.example.olden.cryptoexchange.common.di.AppModule;
import com.example.olden.cryptoexchange.common.di.DaggerAppComponent;
import com.example.olden.cryptoexchange.data.network.api.ApiModule;

public class CryptoExchangeApplication extends Application {

    public static final String BASE_URL = "https://www.cryptocompare.com/api/data/";
    private AppComponent appComponent;

    @NonNull
    public static CryptoExchangeApplication appComponent(@NonNull Context context) {
        return (CryptoExchangeApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = prepareApplicationComponent().build();

    }

    protected DaggerAppComponent.Builder prepareApplicationComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule(BASE_URL));
    }

    @NonNull
    public AppComponent appComponent() {return appComponent;}
}
