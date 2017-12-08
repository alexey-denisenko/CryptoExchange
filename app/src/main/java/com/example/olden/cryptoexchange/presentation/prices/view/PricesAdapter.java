package com.example.olden.cryptoexchange.presentation.prices.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.olden.cryptoexchange.R;
import com.example.olden.cryptoexchange.data.entity.Price;

import java.util.ArrayList;
import java.util.List;


public class PricesAdapter extends RecyclerView.Adapter<PricesViewHolder> {

    private List<Price> priceList = new ArrayList<>();

    @Override
    public PricesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new PricesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PricesViewHolder holder, int position) {
        holder.currencyName.setText(priceList.get(position).name());
        holder.priveValue.setText(priceList.get(position).price());
    }

    @Override
    public int getItemCount() {
        return priceList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.item_price;
    }

    public void setPrices(List<Price> prices) {
        priceList.clear();
        priceList.addAll(prices);
        notifyDataSetChanged();
    }
}
