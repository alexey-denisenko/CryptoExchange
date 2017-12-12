package com.example.olden.cryptoexchange.di.component;

import com.example.olden.cryptoexchange.di.module.CoinsModule;
import com.example.olden.cryptoexchange.di.scope.CurrenciesListScope;
import com.example.olden.cryptoexchange.presentation.currencies_list.view.CurrenciesFragment;

import dagger.Subcomponent;

@CurrenciesListScope
@Subcomponent(modules = {CoinsModule.class})
public interface CurrenciesListComponent {

    void inject(CurrenciesFragment fragment);
}
