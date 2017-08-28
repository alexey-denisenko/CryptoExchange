package com.example.olden.cryptoexchange.presentation.currencies_list.view;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.olden.cryptoexchange.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CurrenciesListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.currency_name)
    TextView textView;

    @BindView(R.id.delete_selected_currency)
    ImageView imageView; //TODO add listener

    public CurrenciesListViewHolder(View itemView, OnCurrencySelectedListener listener) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        itemView.setOnClickListener(view -> listener.onCurrencySelected(getAdapterPosition()));
    }

    interface OnCurrencySelectedListener {
        void onCurrencySelected(int position);
    }

}
