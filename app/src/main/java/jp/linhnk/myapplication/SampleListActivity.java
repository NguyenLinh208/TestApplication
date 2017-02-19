package jp.linhnk.myapplication;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import jp.linhnk.myapplication.adapter.ImageListAdapter;
import jp.linhnk.myapplication.api.ApiObjectCallback;
import jp.linhnk.myapplication.api.GiphyApi;
import jp.linhnk.myapplication.base.BaseActivity;
import jp.linhnk.myapplication.databinding.ActivitySampleListBinding;
import jp.linhnk.myapplication.model.GiphyDataListResponse;

/**
 * Created by usr0200475 on 2017/02/16
 * Copyright © 2017 GMO Media Inc. All rights reserved.
 */

public class SampleListActivity extends BaseActivity {

    ImageListAdapter imageListAdapter;
    ActivitySampleListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configureLayout();
        createObservable();
    }

    private void configureLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sample_list);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
    }

    private void createObservable() {
        GiphyApi api = new GiphyApi();
        api.start(new ApiObjectCallback<GiphyDataListResponse>() {
            @Override
            public void onSuccess(GiphyDataListResponse response) {
                Log.e("onSuccess:", response.data.length+"");
                binding.progress.setVisibility(View.GONE);
                imageListAdapter = new ImageListAdapter(getApplicationContext());
                binding.recyclerView.setAdapter(imageListAdapter);
                imageListAdapter.setDataList(response.data);
            }

            @Override
            public void onFail(int errorCode, String message) {

            }
        });
    }
}
