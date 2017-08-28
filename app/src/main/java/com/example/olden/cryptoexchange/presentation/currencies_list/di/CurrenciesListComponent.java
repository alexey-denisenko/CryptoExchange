package com.example.olden.cryptoexchange.presentation.currencies_list.di;

import com.example.olden.cryptoexchange.presentation.currencies_list.view.CurrenciesFragment;

import dagger.Subcomponent;

@Subcomponent(modules = {CurrenciesListModule.class})
@CurrenciesListScope
public interface CurrenciesListComponent {

    void inject(CurrenciesFragment fragment);
}
