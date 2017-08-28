package com.example.olden.cryptoexchange.presentation.currencies_list.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.olden.cryptoexchange.CryptoExchangeApplication;
import com.example.olden.cryptoexchange.IntentKey;
import com.example.olden.cryptoexchange.R;
import com.example.olden.cryptoexchange.presentation.currencies_list.di.CurrenciesListModule;
import com.example.olden.cryptoexchange.presentation.currencies_list.presenter.ICurrenciesPresenter;
import com.example.olden.cryptoexchange.presentation.prices.PricesActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CurrenciesFragment extends Fragment implements ICurrenciesView,
        AdapterView.OnItemClickListener, CurrenciesListViewHolder.OnCurrencySelectedListener {

    private static final String TAG = "CurrenciesFragment";

    @BindView(R.id.currencies_search_tv)
    AutoCompleteTextView searchTextView;

    @BindView(R.id.currencies_list)
    RecyclerView recyclerView;

    @Inject
    ICurrenciesPresenter presenter;

    private CurrenciesListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        CryptoExchangeApplication.appComponent(getActivity()).appComponent().plus(new CurrenciesListModule()).inject(this);

        View view = inflater.inflate(R.layout.fragment_currencies, container, false);
        ButterKnife.bind(this, view);

        searchTextView.setOnItemClickListener(this);

        adapter = new CurrenciesListAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        presenter.bindView(this);
        presenter.fillAutoCompleteList();
        return view;
    }

    @Override
    public void onDestroyView() {
        presenter.unBindView();
        super.onDestroyView();
    }

    @Override
    public void setAutoCompleteTextView(List<String> currencies) {

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_dropdown_item_1line,
                currencies
        );
        searchTextView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String currencyName = (String) parent.getItemAtPosition(position);
        adapter.addCurrency(currencyName);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onCurrencySelected(int position) {
        String currencyName = adapter.getCurrencies().get(position);
        Intent intent = new Intent(getActivity(), PricesActivity.class);
        intent.putExtra(IntentKey.CURRENCY_NAME, currencyName);
        startActivity(intent);
    }
}
