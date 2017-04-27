package jp.linhnk.myapplication.api;

import android.util.Log;

import java.util.HashMap;

import jp.linhnk.myapplication.AppConstant;
import jp.linhnk.myapplication.model.GiphyDataListResponse;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by usr0200475 on 2017/02/16.
 * Copyright © 2017 GMO Media Inc. All rights reserved.
 */

public class GiphyApi extends BaseApiController<GiphyDataListResponse> {

    public GiphyDataListResponse giphyDataListResponse;

    public HashMap<String, String> getAddParams() {
        return addParams;
    }

    public void setAddParams(HashMap<String, String> addParams) {
        this.addParams = addParams;
    }

    public GiphyDataListResponse getGiphyDataListResponse() {
        return giphyDataListResponse;
    }

    public void setGiphyDataListResponse(GiphyDataListResponse giphyDataListResponse) {
        this.giphyDataListResponse = giphyDataListResponse;
    }

    public HashMap<String, String> addParams;

    @Override
    public String getBaseUrl() {
        return AppConstant.GiphyBaseUrl;
    }

    @Override
    public void createRetrofit(Retrofit retrofit) {
        GiphyApiService giphyApiService = retrofit.create(GiphyApiService.class);
        Call<GiphyDataListResponse> call = giphyApiService.search(createParams());
        Log.e("URL:", call.request().toString());
        call.enqueue(this);
    }

    @Override
    public HashMap<String, String> createParams() {
        HashMap<String, String> params = new HashMap<>();
        params.put("q", "cat");
        params.put("api_key", AppConstant.GiphyApiKey);
        params.put("limit", "27");
        params.putAll(getAddParams());
        return params;
    }

    @Override
    public void onResponse(Call<GiphyDataListResponse> call, Response<GiphyDataListResponse> response) {
        if (response.isSuccessful()) {
            giphyDataListResponse = response.body();
            callBack.onSuccess(giphyDataListResponse);
        }
    }

    @Override
    public void onFailure(Call<GiphyDataListResponse> call, Throwable t) {

    }
}
