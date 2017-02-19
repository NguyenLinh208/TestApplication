package jp.linhnk.myapplication.api;

import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;
import jp.linhnk.myapplication.AppConstant;
import jp.linhnk.myapplication.model.GiphyDataListResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.FieldMap;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by usr0200475 on 2017/02/16.
 * Copyright © 2017 GMO Media Inc. All rights reserved.
 */

public interface GiphyApiService {

    //Search Giphy
    @GET(AppConstant.GiphySearch)
    Call<GiphyDataListResponse> search(@QueryMap HashMap<String, String> params);
}
