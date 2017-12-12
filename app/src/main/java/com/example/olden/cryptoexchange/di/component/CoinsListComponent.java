package com.example.olden.cryptoexchange.di.component;

import com.example.olden.cryptoexchange.di.module.CoinsListModule;
import com.example.olden.cryptoexchange.di.scope.CoinsListScope;
import com.example.olden.cryptoexchange.presentation.coins.view.CoinsFragment;

import dagger.Subcomponent;

@CoinsListScope
@Subcomponent(modules = {CoinsListModule.class})
public interface CoinsListComponent {

    void inject(CoinsFragment fragment);
}
