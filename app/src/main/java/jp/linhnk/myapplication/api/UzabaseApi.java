package jp.linhnk.myapplication.api;

import android.util.Log;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

import jp.linhnk.myapplication.model.uzabase.Channel;
import jp.linhnk.myapplication.model.uzabase.Feed;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by usr0200475 on 2017/03/07.
 * Copyright © 2017 GMO Media Inc. All rights reserved.
 */

public class UzabaseApi extends BaseApiController<Feed> {

    public Feed feed;

    @Override
    public void start(ApiObjectCallback<Feed> callback) {
        this.callBack = callback;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getBaseUrl())
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        createRetrofit(retrofit);
    }

    @Override
    public String getBaseUrl() {
        return "http://tech.uzabase.com";
    }

    @Override
    public void createRetrofit(Retrofit retrofit) {
        UzabaseRssService uzabaseRssService = retrofit.create(UzabaseRssService.class);
        Call<Feed> call = uzabaseRssService.getContents();
        Log.e("URL:", call.request().toString());
        call.enqueue(this);
    }

    @Override
    public HashMap<String, String> createParams() {
        return null;
    }

    @Override
    public void onResponse(Call<Feed> call, Response<Feed> response) {
        if (response.isSuccessful()) {
            feed = response.body();
            callBack.onSuccess(feed);
        }
    }

    @Override
    public void onFailure(Call<Feed> call, Throwable t) {
        callBack.onFail(0, t.getMessage());
    }
}
