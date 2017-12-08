package com.example.olden.cryptoexchange.data.network;

import android.support.annotation.NonNull;

import com.example.olden.cryptoexchange.data.entity.CoinsData;
import com.example.olden.cryptoexchange.data.entity.PricesData;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CryptoCompareApi {

    @GET("coinlist/") @NonNull
    Single<CoinsData> getCoinsData();

    @GET("price/")
    Single<PricesData> getPrices(@Query("fsym") String from, @Query("tsyms") List<String> to);

}