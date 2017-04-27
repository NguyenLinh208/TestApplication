package jp.linhnk.myapplication.api;

import jp.linhnk.myapplication.model.uzabase.Channel;
import jp.linhnk.myapplication.model.uzabase.Feed;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by usr0200475 on 2017/03/07.
 * Copyright © 2017 GMO Media Inc. All rights reserved.
 */

public interface UzabaseRssService {

    @GET("/rss")
    Call<Feed> getContents();
}
