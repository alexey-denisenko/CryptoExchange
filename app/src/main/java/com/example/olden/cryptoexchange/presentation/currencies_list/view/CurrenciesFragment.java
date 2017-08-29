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
import com.example.olden.cryptoexchange.R;
import com.example.olden.cryptoexchange.other.keys.IntentKey;
import com.example.olden.cryptoexchange.presentation.currencies_list.di.CurrenciesListModule;
import com.example.olden.cryptoexchange.presentation.currencies_list.presenter.ICurrenciesPresenter;
import com.example.olden.cryptoexchange.presentation.prices.view.PricesActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CurrenciesFragment extends Fragment implements ICurrenciesView,
        AdapterView.OnItemClickListener, CurrenciesListViewHolder.OnCurrencySelectedListener {

    private static final String TAG = "CurrenciesFragment";

    @BindView(R.id.currencies_search_tv)
    AutoCompleteTextView searchTextView;

    @BindView(R.id.currencies_list)
    RecyclerView recyclerView;

    @Inject
    ICurrenciesPresenter presenter;

    private CurrenciesListAdapter currenciesListAdapter;

    private ArrayAdapter<String> searchViewAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        CryptoExchangeApplication.appComponent(getActivity()).appComponent().plus(new CurrenciesListModule()).inject(this);

        View view = inflater.inflate(R.layout.fragment_currencies, container, false);
        ButterKnife.bind(this, view);

        searchTextView.setOnItemClickListener(this);

        initRecyclerView();

        presenter.bindView(this);
        presenter.fillAutoCompleteList();
        return view;
    }

    @Override
    public void onDestroyView() {
        presenter.saveCurrencyNamesToStorage(currenciesListAdapter.getCurrencies());
        presenter.unBindView();
        super.onDestroyView();
    }

    @Override
    public void setAutoCompleteTextView(List<String> currencies) {

        searchViewAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_dropdown_item_1line,
                currencies
        );
        searchTextView.setAdapter(searchViewAdapter);
    }

    @Override
    public void showSavedCurrenciesList(List<String> currencies) {
        currenciesListAdapter.addCurrencyList(currencies);
    }

    @Override
    public void showSearchView() {
        searchTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setFocusOnSearchView() {
        searchTextView.requestFocus();
    }

    @Override
    public void hideSearchView() {
        searchTextView.setVisibility(View.GONE);
    }

    @Override
    public void cleanSearchView() {
        searchTextView.setText("");
    }

    @Override
    public void showNewCurrencyItem(String name) {
        currenciesListAdapter.addCurrency(name);
        currenciesListAdapter.notifyDataSetChanged();
    }

    @Override
    public void removeCurrencyFromSearch(String name) {
        searchViewAdapter.remove(name);
        searchViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String currencyName = (String) parent.getItemAtPosition(position);
        presenter.addCurrencyItem(currencyName);
    }

    @Override
    public void onCurrencySelected(int position) {
        String currencyName = currenciesListAdapter.getCurrencies().get(position);
        Intent intent = new Intent(getActivity(), PricesActivity.class);
        intent.putExtra(IntentKey.CURRENCY_NAME, currencyName);
        startActivity(intent);
    }

    @OnClick(R.id.add_button)
    public void onAddButtonClick() {
        presenter.showAddCurrencies();
    }

    private void initRecyclerView() {
        currenciesListAdapter = new CurrenciesListAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(currenciesListAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    }
}
