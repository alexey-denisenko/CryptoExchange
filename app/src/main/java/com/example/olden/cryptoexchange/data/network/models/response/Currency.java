package com.example.olden.cryptoexchange.data.network.models.response;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class Currency {

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
        return new AutoValue_Currency.Builder();
    }

    @NonNull
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
