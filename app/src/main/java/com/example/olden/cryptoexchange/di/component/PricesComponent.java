package com.example.olden.cryptoexchange.di.component;


import com.example.olden.cryptoexchange.di.module.PricesModule;
import com.example.olden.cryptoexchange.di.scope.PricesScope;
import com.example.olden.cryptoexchange.presentation.prices.view.PricesFragment;

import dagger.Subcomponent;

@PricesScope
@Subcomponent(modules = {PricesModule.class})
public interface PricesComponent {

    void inject(PricesFragment fragment);
}
