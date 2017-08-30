package com.example.olden.cryptoexchange.common.mvp;


public interface ICommonPresenter<V extends ICommonView> {

    void bindView(V view);

    void unBindView();
}
