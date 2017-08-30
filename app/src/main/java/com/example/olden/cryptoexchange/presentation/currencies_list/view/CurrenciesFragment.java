package com.example.olden.cryptoexchange.presentation.currencies_list.view;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.olden.cryptoexchange.common.CryptoExchangeApplication;
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

    @BindView(R.id.currencies_search_tv)
    AutoCompleteTextView searchTextView;

    @BindView(R.id.currencies_list)
    RecyclerView recyclerView;

    @BindView(R.id.add_button)
    FloatingActionButton fabAdd;

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout refreshLayout;

    @Inject
    ICurrenciesPresenter<ICurrenciesView> presenter;

    private CurrenciesListAdapter currenciesListAdapter;
    private ArrayAdapter<String> searchViewAdapter;
    private Snackbar snackbar;
    private InputMethodManager inputMethodManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CryptoExchangeApplication.appComponent(getActivity()).appComponent().plus(new CurrenciesListModule()).inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_currencies, container, false);
        ButterKnife.bind(this, view);

        searchTextView.setOnItemClickListener(this);
        refreshLayout.setEnabled(false);

        initRecyclerView();

        inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        snackbar = Snackbar.make(view, "Error loading currencies list", Snackbar.LENGTH_INDEFINITE)
                .setAction("Retry", clickView -> {
                    presenter.fillAutoCompleteList(true);
                });

        presenter.bindView(this);
        presenter.fillAutoCompleteList(false);
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
        inputMethodManager.showSoftInput(searchTextView, InputMethodManager.SHOW_IMPLICIT);
    }

    @Override
    public void hideKeyboard() {
        inputMethodManager.hideSoftInputFromWindow(searchTextView.getWindowToken(), 0);
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
    public void showLoading() {
        refreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void enableAddButton() {
        fabAdd.setEnabled(true);
    }

    @Override
    public void disableAddButton() {
        fabAdd.setEnabled(false);
    }

    @Override
    public void showErrorLoading() {
        if (!snackbar.isShown()) {
            snackbar.show();
        }
    }

    @Override
    public void showNewCurrencyItem(String name) {
        currenciesListAdapter.addCurrency(name);
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
