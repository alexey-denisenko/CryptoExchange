package com.example.olden.cryptoexchange.data.entity;


import android.support.annotation.NonNull;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

@AutoValue
public abstract class CoinsData {

    @NonNull
    @SerializedName("Data")
    public abstract HashMap<String, Currency> data();

    @NonNull
    public static CoinsData.Builder builder() {
        return new $AutoValue_CoinsData.Builder();
    }

    @NonNull
    public static TypeAdapter<CoinsData> typeAdapter(Gson gson) {
        return new AutoValue_CoinsData.GsonTypeAdapter(gson);
    }

    @AutoValue.Builder
    public static abstract class Builder {

        @NonNull
        public abstract CoinsData.Builder data(@NonNull HashMap<String, Currency> data);

        @NonNull
        public abstract CoinsData build();
    }
}
