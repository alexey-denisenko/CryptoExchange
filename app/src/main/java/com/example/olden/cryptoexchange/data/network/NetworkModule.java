package com.example.olden.cryptoexchange.data.network;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

@Module
public class NetworkModule {

    @Provides @NonNull @Singleton
    public OkHttpClient provideOkHttpClient(@NonNull List<Interceptor> interceptors) {
        final OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();

        for (Interceptor interceptor : interceptors) {
            okHttpBuilder.addInterceptor(interceptor);
        }

        return okHttpBuilder.build();
    }
}
