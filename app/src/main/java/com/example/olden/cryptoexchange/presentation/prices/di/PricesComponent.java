package com.example.olden.cryptoexchange.presentation.prices.di;


import com.example.olden.cryptoexchange.presentation.prices.view.PricesFragment;

import dagger.Subcomponent;

@PricesScope
@Subcomponent(modules = {PricesModule.class})
public interface PricesComponent {

    void inject(PricesFragment fragment);
}
