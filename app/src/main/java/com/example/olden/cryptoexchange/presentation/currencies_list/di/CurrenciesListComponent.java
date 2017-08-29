package com.example.olden.cryptoexchange.presentation.currencies_list.di;

import com.example.olden.cryptoexchange.presentation.currencies_list.view.CurrenciesFragment;

import dagger.Subcomponent;

@CurrenciesListScope
@Subcomponent(modules = {CurrenciesListModule.class})
public interface CurrenciesListComponent {

    void inject(CurrenciesFragment fragment);
}
