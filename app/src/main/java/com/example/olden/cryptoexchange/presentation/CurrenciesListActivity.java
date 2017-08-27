package com.example.olden.cryptoexchange.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.olden.cryptoexchange.CryptoExchangeApplication;
import com.example.olden.cryptoexchange.R;
import com.example.olden.cryptoexchange.data.network.api.CryptoCompareService;

import javax.inject.Inject;

public class CurrenciesListActivity extends AppCompatActivity {

    @Inject
    CryptoCompareService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currencies_list);
        CryptoExchangeApplication.get(this).get().inject(this);
    }
}
