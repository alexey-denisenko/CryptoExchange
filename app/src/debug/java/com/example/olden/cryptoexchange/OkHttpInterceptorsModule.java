package com.example.olden.cryptoexchange;


import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.logging.HttpLoggingInterceptor;

import static java.util.Collections.singletonList;

@Module
public class OkHttpInterceptorsModule {

    @Provides @Singleton @NonNull
    public List<Interceptor> provideOkHttpInterceptors() {
        return singletonList(new HttpLoggingInterceptor());
    }
}
