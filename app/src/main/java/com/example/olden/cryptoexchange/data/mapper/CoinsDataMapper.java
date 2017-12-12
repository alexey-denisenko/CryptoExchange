package com.example.olden.cryptoexchange.data.mapper;


import com.example.olden.cryptoexchange.data.entity.CoinsData;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CoinsDataMapper {

    @Inject
    public CoinsDataMapper() {}

    public List<String> transform(CoinsData coinsData) {
        Set<String> keys = coinsData.data().keySet();
        return new ArrayList<>(keys);
    }
}
