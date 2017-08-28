package com.example.olden.cryptoexchange.other.preferences;

import java.util.Set;

public interface StringSetPreferenceType {

    Set<String> get();

    boolean isSet();

    void set(Set<String> value);

    void delete();
}