package com.example.olden.cryptoexchange.presentation.coins.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.olden.cryptoexchange.R;

import java.util.ArrayList;
import java.util.List;

public class CoinsListAdapter extends RecyclerView.Adapter<CoinsListViewHolder>{

    private List<String> coins = new ArrayList<>();

    private CoinsListViewHolder.OnCurrencySelectedListener listener;

    public CoinsListAdapter(CoinsListViewHolder.OnCurrencySelectedListener listener) {
        this.listener = listener;
    }

    @Override
    public CoinsListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new CoinsListViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(CoinsListViewHolder holder, int position) {
        holder.textView.setText(coins.get(position));
    }

    @Override
    public int getItemCount() {
        return coins.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.item_coin;
    }

    public void addCoin(String name) {
        coins.add(name);
        notifyDataSetChanged();
    }

    public void addSeveralCoins(List<String> names) {
        coins.addAll(names);
        notifyDataSetChanged();
    }

    public List<String> getCoins() {
        return coins;
    }
}
