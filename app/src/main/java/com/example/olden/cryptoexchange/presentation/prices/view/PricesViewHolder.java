package com.example.olden.cryptoexchange.presentation.prices.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.olden.cryptoexchange.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PricesViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.currency_name)
    TextView currencyName;

    @BindView(R.id.price_value)
    TextView priveValue;

    public PricesViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
