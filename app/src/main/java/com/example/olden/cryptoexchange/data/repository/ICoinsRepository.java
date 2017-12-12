package com.example.olden.cryptoexchange.data.repository;

import java.util.List;
import java.util.Set;

import io.reactivex.Observable;

public interface ICoinsRepository {

    Observable<List<String>> getCoinsData();

    Observable<List<String>> refreshCoinsData();

    Observable<Set<String>> getSelectedCoins();

    void saveSelectedCoins(Set<String> coins);

    void saveSelectedCoins(String coin);
}
