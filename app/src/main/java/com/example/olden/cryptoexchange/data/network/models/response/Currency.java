package com.example.olden.cryptoexchange.data.network.models.response;

import android.support.annotation.NonNull;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class Currency {

    @SerializedName("Id")
    public abstract int id();

    @SerializedName("ImageUrl")
    public abstract String imageUrl();

    @SerializedName("Name")
    public abstract String name();

    public static TypeAdapter<Currency> typeAdapter(Gson gson) {
        return new AutoValue_Currency.GsonTypeAdapter(gson);
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
        public abstract Currency build();
    }
}
