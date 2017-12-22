package com.example.olden.cryptoexchange.data.repository;

import com.example.olden.cryptoexchange.data.entity.Price;

import java.util.List;
import java.util.Set;

import io.reactivex.Observable;

public interface ICoinsRepository {

    Observable<List<String>> getCoinsData();

    Observable<List<String>> refreshCoinsData();

    Observable<Set<String>> getSelectedCoins();

    Observable<List<Price>> getPrices(String from, List<String> to);

    void saveSelectedCoins(Set<String> coins);

    void saveSelectedCoins(String coin);
}
