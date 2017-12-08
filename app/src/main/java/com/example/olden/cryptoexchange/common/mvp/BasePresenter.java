package com.example.olden.cryptoexchange.common.mvp;

import android.support.annotation.Nullable;

import io.reactivex.disposables.CompositeDisposable;

public class BasePresenter<V extends ICommonView> implements ICommonPresenter<V> {

    @Nullable
    private V view;

    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    protected final V getViewOrThrow() {
        if (view == null) {
            throw new IllegalStateException("View not attached");
        }
        return view;
    }

    @Override
    public void bindView(V view) {
        this.view = view;
    }

    @Override
    public final void unBindView() {
        this.view = null;
        compositeDisposable.clear();
    }
}
