package com.example.olden.cryptoexchange.presentation.currencies_list.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.olden.cryptoexchange.R;

import java.util.ArrayList;
import java.util.List;

public class CurrenciesListAdapter extends RecyclerView.Adapter<CurrenciesListViewHolder>{

    private List<String> currencies = new ArrayList<>();

    private CurrenciesListViewHolder.OnCurrencySelectedListener listener;

    public CurrenciesListAdapter(CurrenciesListViewHolder.OnCurrencySelectedListener listener) {
        this.listener = listener;
    }

    @Override
    public CurrenciesListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new CurrenciesListViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(CurrenciesListViewHolder holder, int position) {
        holder.textView.setText(currencies.get(position));
    }

    @Override
    public int getItemCount() {
        return currencies.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.item_currency;
    }

    public void addCurrency(String name) {
        currencies.add(name);
        notifyDataSetChanged();
    }

    public void addCurrencyList(List<String> names) {
        currencies.addAll(names);
        notifyDataSetChanged();
    }

    public List<String> getCurrencies() {
        return currencies;
    }
}
