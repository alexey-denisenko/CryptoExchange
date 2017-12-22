package com.example.olden.cryptoexchange.data.repository.cache;

import com.example.olden.cryptoexchange.data.entity.Price;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;

@Singleton
public class CoinPricesCache {

   private Map<String, List<Price>> pricesCache = new HashMap<>();
    private boolean isCacheUpToDate = false;

    public CoinPricesCache() {
    }

    public List<Price> get(String coin, List<String> currencies) {
        coin = coin.toUpperCase();
        List<Price> result = new ArrayList<>();

        for(Price price : pricesCache.get(coin)) {
            if(currencies.contains(price.name())) {
                result.add(price);
            }
        }
        return result;
    }

    public void set(String coin, List<Price> prices) {
        coin = coin.toUpperCase();
        pricesCache.put(coin, prices);
        setUpToDate(true);
    }

    public boolean isCached(String coin) {
        coin = coin.toUpperCase();
        return pricesCache.get(coin) != null;
    }

    public boolean isExpired() {
        return isCacheUpToDate;
    }

    private void setUpToDate(boolean cacheUpToDate) {
        isCacheUpToDate = cacheUpToDate;
    }

    public void expire() {
        setUpToDate(false);
    }
}
