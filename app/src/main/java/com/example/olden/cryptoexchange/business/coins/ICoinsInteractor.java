package com.example.olden.cryptoexchange.business.coins;


import java.util.List;

import io.reactivex.Observable;

public interface ICoinsInteractor {

    Observable<List<String>> getCoinNamesList(boolean forceRefresh);

    Observable<List<String>> getSelectedCoinsList();

    void saveSelectedCoins(List<String> coins);

    void saveSelectedCoins(String name);
}
