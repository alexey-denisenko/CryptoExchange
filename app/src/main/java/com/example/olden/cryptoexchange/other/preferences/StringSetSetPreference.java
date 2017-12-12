package com.example.olden.cryptoexchange.other.preferences;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class StringSetSetPreference implements StringSetPreferenceType {
    private final SharedPreferences sharedPreferences;
    private final String key;
    private final Set<String> defaultValue;

    @Inject
    public StringSetSetPreference(final @NonNull SharedPreferences sharedPreferences, final @NonNull String key) {
        this(sharedPreferences, key, null);
    }

    public StringSetSetPreference(final @NonNull SharedPreferences sharedPreferences, final @NonNull String key,
                                  final @Nullable Set<String> defaultValue) {
        this.sharedPreferences = sharedPreferences;
        this.key = key;
        this.defaultValue = defaultValue;
    }

    @Override
    public Set<String> get() {
        return this.sharedPreferences.getStringSet(this.key, this.defaultValue);
    }

    @Override
    public boolean contains() {
        return this.sharedPreferences.contains(this.key);
    }

    @Override
    public void set(final @NonNull Set<String> value) {
        this.sharedPreferences.edit().putStringSet(this.key, value).apply();
    }

    @Override
    public void add(String value) {

        Set<String> savedValues = this.get();
        savedValues.add(value);

        this.set(savedValues);
    }

    @Override
    public void delete() {
        this.sharedPreferences.edit().remove(this.key).apply();
    }
}
