package com.example.olden.cryptoexchange.presentation.prices.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.olden.cryptoexchange.CryptoExchangeApplication;
import com.example.olden.cryptoexchange.R;
import com.example.olden.cryptoexchange.data.network.models.response.Price;
import com.example.olden.cryptoexchange.other.keys.IntentKey;
import com.example.olden.cryptoexchange.presentation.prices.di.PricesModule;
import com.example.olden.cryptoexchange.presentation.prices.presenter.IPricesPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PricesFragment extends Fragment implements IPricesView {

    @BindView(R.id.prices_recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.currency_name)
    TextView baseCurrencyName;

    @Inject
    IPricesPresenter<IPricesView> presenter;

    private Snackbar snackbar;

    private PricesAdapter adapter;

    public static PricesFragment newInstance(String baseCurrency) {

        Bundle args = new Bundle();
        args.putString(IntentKey.CURRENCY_NAME, baseCurrency);

        PricesFragment fragment = new PricesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        CryptoExchangeApplication.appComponent(getActivity()).appComponent().plus(new PricesModule()).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_prices, container, false);
        ButterKnife.bind(this, view);

        initializeRecyclerView();

        String baseCurrency = getArguments().getString(IntentKey.CURRENCY_NAME);
        baseCurrencyName.setText(baseCurrency);

        swipeRefreshLayout.setEnabled(false);
        presenter.bindView(this);
        presenter.subscribeOnPricesUpdate(baseCurrency);

        snackbar = Snackbar.make(container, R.string.no_data_available, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.back, clickView -> getActivity().finish());

        return view;
    }

    @Override
    public void onDestroy() {
        presenter.unBindView();
        super.onDestroy();
    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showNoDataLoaded() {

        if (!snackbar.isShown()) {
            snackbar.show();
        }
    }

    @Override
    public void showNewPrices(List<Price> prices) {
        adapter.setPrices(prices);
    }

    private void initializeRecyclerView() {
        adapter = new PricesAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    }
}
