package com.example.olden.cryptoexchange.data.network.models.response;

import android.support.annotation.NonNull;

import com.google.auto.value.AutoValue;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class Price {

    @NonNull
    @SerializedName("Symbol")
    public abstract String name();

    @NonNull
    @SerializedName("Price")
    public abstract String price();

    @NonNull
    public static Price.Builder builder() {return new AutoValue_Price.Builder();}

    @AutoValue.Builder
    public static abstract class Builder {

        @NonNull
        public abstract Price.Builder name(@NonNull String name);

        @NonNull
        public abstract Price.Builder price(@NonNull String price);

        @NonNull
        public abstract Price build();
    }
}