package com.example.olden.cryptoexchange.data.network.api;

import android.support.annotation.NonNull;

import com.example.olden.cryptoexchange.data.network.models.response.CoinsData;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface CryptoCompareService {

    @GET("api/data/coinlist/") @NonNull
    Single<CoinsData> getCoinsData();

}
