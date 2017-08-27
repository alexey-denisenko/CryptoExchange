package com.example.olden.cryptoexchange;

import com.google.gson.TypeAdapterFactory;
import com.ryanharter.auto.value.gson.GsonTypeAdapterFactory;

@GsonTypeAdapterFactory
public abstract class ResponseTypeAdapterFactory implements TypeAdapterFactory{

    public static TypeAdapterFactory create() { return new AutoValueGson_ResponseTypeAdapterFactory();}
}
