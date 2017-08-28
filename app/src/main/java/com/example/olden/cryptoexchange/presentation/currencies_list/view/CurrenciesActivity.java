package com.example.olden.cryptoexchange.presentation.currencies_list.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.olden.cryptoexchange.R;

public class CurrenciesActivity extends AppCompatActivity {

    private static final String TAG = "CurrenciesActivity";

//    @Inject
//    ICurrenciesInteractor interactor;
//
//    @Inject
//    ICurrenciesRepository repository;
//
//    private CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currencies_list);
//        CryptoExchangeApplication.appComponent(this).appComponent().plus(new CurrenciesListModule()).inject(this);


//        compositeDisposable = new CompositeDisposable();
//
//        Disposable disposable = Observable.interval(5, TimeUnit.SECONDS)
//                .flatMap(time -> {
//                    Log.d(TAG, "onCreate: " + time);
//                    return repository.getPrices("BTC", Arrays.asList("USD", "EUR")).toObservable();
//                })
//                .retry()
//                .subscribe(
//                        pricesData -> Log.d(TAG, "Success " + pricesData.toString()),
//                        throwable -> Log.e(TAG, "Fail: ", throwable)
//                );
//
//        compositeDisposable.add(disposable);


    }

    public void unSubscribe(View view) {
//        compositeDisposable.clear();
    }
}
