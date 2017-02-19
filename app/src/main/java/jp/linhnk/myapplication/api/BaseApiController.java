package jp.linhnk.myapplication.api;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

import javax.inject.Singleton;

import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by usr0200475 on 2017/02/18.
 * Copyright © 2017 GMO Media Inc. All rights reserved.
 */

@Singleton
public abstract class BaseApiController<T> implements Callback<T>{

    public ApiObjectCallback<T> callBack;

    public void start(ApiObjectCallback<T> callback) {
        this.callBack = callback;
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        createRetrofit(retrofit);
    }

    public abstract String getBaseUrl();

    public abstract void createRetrofit(Retrofit retrofit);

    public abstract HashMap<String, String> createParams();
}
