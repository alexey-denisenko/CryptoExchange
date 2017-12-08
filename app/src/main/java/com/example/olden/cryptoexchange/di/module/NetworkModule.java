package com.example.olden.cryptoexchange.di.module;

import android.support.annotation.NonNull;

import com.example.olden.cryptoexchange.data.network.CryptoCompareApi;
import com.example.olden.cryptoexchange.other.ResponseTypeAdapterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapterFactory;

import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.olden.cryptoexchange.di.Qualifiers.IO_THREAD;
import static com.example.olden.cryptoexchange.di.Qualifiers.UI_THREAD;

@Module
public class NetworkModule {

    private String baseUrl;

    public NetworkModule(@NonNull String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides @NonNull @Singleton
    public CryptoCompareApi provideCryptoCompareApi(Retrofit retrofit) {
        return retrofit.create(CryptoCompareApi.class);
    }

    @Provides @NonNull @Singleton
    public Retrofit provideRetrofit(@NonNull OkHttpClient okHttpClient, @NonNull Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides @NonNull @Singleton
    public OkHttpClient provideOkHttpClient(@NonNull List<Interceptor> interceptors) {
        final OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();

        for (Interceptor interceptor : interceptors) {
            okHttpBuilder.addInterceptor(interceptor);
        }

        return okHttpBuilder.build();
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

    @Provides @NonNull @Singleton @Named(IO_THREAD)
    public Scheduler provideIoScheduler() {
        return Schedulers.io();
    }

    @Provides @NonNull @Singleton @Named(UI_THREAD)
    public Scheduler provideUiScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
