package com.example.olden.cryptoexchange;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    @Provides @NonNull
    public Gson providesGson() {
        return new Gson();
    }
}
