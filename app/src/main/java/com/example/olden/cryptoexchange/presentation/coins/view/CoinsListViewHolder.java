package com.example.olden.cryptoexchange.presentation.coins.view;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.olden.cryptoexchange.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CoinsListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.currency_name)
    TextView textView;

    public CoinsListViewHolder(View itemView, OnCurrencySelectedListener listener) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        itemView.setOnClickListener(view -> listener.onCurrencySelected(getAdapterPosition()));
    }

    interface OnCurrencySelectedListener {
        void onCurrencySelected(int position);
    }

}
