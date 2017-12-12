package com.example.olden.cryptoexchange.data.entity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class Coin {

    @SerializedName("Id")
    public abstract int id();

    @Nullable
    @SerializedName("ImageUrl")
    public abstract String imageUrl();

    @NonNull
    @SerializedName("Name")
    public abstract String name();

    @NonNull
    public static Builder builder() {
        return new $AutoValue_Coin.Builder();
    }

    @NonNull
    public static TypeAdapter<Coin> typeAdapter(Gson gson) {
        return new AutoValue_Coin.GsonTypeAdapter(gson);
    }

    @AutoValue.Builder
    public static abstract class Builder {

        @NonNull
        public abstract Builder id(int id);

        @NonNull
        public abstract Builder imageUrl(@NonNull String imageUrl);

        @NonNull
        public abstract Builder name(@NonNull String name);

        @NonNull
        public abstract Coin build();
    }
}
