package com.example.olden.cryptoexchange.presentation.coins.presenter;

import com.example.olden.cryptoexchange.mvp.ICommonPresenter;
import com.example.olden.cryptoexchange.mvp.ICommonView;

import java.util.List;

public interface ICoinsPresenter<V extends ICommonView> extends ICommonPresenter<V> {

    void fillAutoCompleteList(boolean forceUpdate);
    void saveSelectedCoins(List<String> names);

    void showCoinsSearch();
    void addSelectedCoin(String name);
}
