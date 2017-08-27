package com.example.olden.cryptoexchange;


import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;

import static java.util.Collections.emptyList;

@Module
public class OkHttpInterceptorsModule {

    @Provides @Singleton @NonNull
    public List<Interceptor> provideOkHttpInterceptors() {
        return emptyList();
    }
}
