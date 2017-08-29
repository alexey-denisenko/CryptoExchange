package com.example.olden.cryptoexchange.common;


public interface ICommonPresenter<V> {

    void bindView(V view);

    void unBindView();
}
