package com.example.olden.cryptoexchange.data.network.models.response;


import android.support.annotation.NonNull;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@AutoValue
public abstract class PricesData {

    @NonNull
    @SerializedName("Data")
    public abstract List<Price> data();

    @NonNull
    public static PricesData.Builder builder() {
        return new AutoValue_PricesData.Builder();
    }

    @NonNull
    public static TypeAdapter<PricesData> typeAdapter(Gson gson) {
        return new AutoValue_PricesData.GsonTypeAdapter(gson);
    }

    @AutoValue.Builder
    public static abstract class Builder {

        @NonNull
        public abstract PricesData.Builder data(@NonNull List<Price> data);

        @NonNull
        public abstract PricesData build();
    }

    @Override
    public String toString() {
        StringBuilder strBuilder = new StringBuilder();
        for(Price price : data()) {
            strBuilder.append(price.toString()).append("\n");
        }
        return strBuilder.toString();
    }
}
