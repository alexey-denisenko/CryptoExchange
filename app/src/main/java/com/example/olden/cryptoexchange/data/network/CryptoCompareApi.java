package com.example.olden.cryptoexchange.data.network;

import android.support.annotation.NonNull;

import com.example.olden.cryptoexchange.data.entity.CoinsData;
import com.example.olden.cryptoexchange.data.entity.PricesData;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CryptoCompareApi {

    @GET("coinlist/") @NonNull
    Observable<CoinsData> getCoinsData();

    @GET("price/")
    Observable<PricesData> getPrices(@Query("fsym") String from, @Query("tsyms") List<String> to);

}
