package com.example.olden.cryptoexchange.presentation.prices.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.olden.cryptoexchange.R;
import com.example.olden.cryptoexchange.other.keys.IntentKey;

public class PricesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prices);

        Bundle extras = getIntent().getExtras();
        String currencyName;
        if (extras != null) {
            currencyName = extras.getString(IntentKey.CURRENCY_NAME);
        } else {
            throw new RuntimeException("Currency name must be provided to show prices");
        }

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.prices_fragment, PricesFragment.newInstance(currencyName))
                    .commit();
        }
    }
}
