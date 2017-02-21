package jp.linhnk.myapplication;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jp.linhnk.myapplication.adapter.GridLoadMoreAdapter;
import jp.linhnk.myapplication.api.ApiObjectCallback;
import jp.linhnk.myapplication.api.GiphyApi;
import jp.linhnk.myapplication.base.BaseActivity;
import jp.linhnk.myapplication.databinding.ActivitySampleListBinding;
import jp.linhnk.myapplication.model.GiphyDataListResponse;
import jp.linhnk.myapplication.model.datamodel.GiphyRecyclerModel;
import jp.linhnk.myapplication.model.datamodel.RecyclerModel;
import jp.linhnk.myapplication.model.giphy.GiphyImage;
import jp.linhnk.myapplication.model.recyclermodel.LoadMoreModel;

/**
 * Created by usr0200475 on 2017/02/16
 * Copyright © 2017 GMO Media Inc. All rights reserved.
 */

public class SampleListActivity extends BaseActivity {

    GridLoadMoreAdapter imageListAdapter;
    ActivitySampleListBinding binding;
    protected List<RecyclerModel> dataSet = new ArrayList<>();
    protected LoadMoreModel loadMoreModel = new LoadMoreModel();
    private int offset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageListAdapter = new GridLoadMoreAdapter();
        configureLayout();
        requestData();
    }

    private void configureLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sample_list);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        binding.recyclerView.setHasFixedSize(true);
        setupRecyclerView();
        imageListAdapter.setOnLoadMore(new GridLoadMoreAdapter.OnLoadMore() {
            @Override
            public void onLoadMore() {
                requestData();
            }
        });
    }

    private void setupRecyclerView() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return imageListAdapter.getSpanSize(position);
            }
        });

        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(imageListAdapter);
    }

    private void requestData() {
        GiphyApi api = new GiphyApi();
        api.setAddParams(loadMoreParams());
        api.start(new ApiObjectCallback<GiphyDataListResponse>() {
            @Override
            public void onSuccess(GiphyDataListResponse response) {
                handlRequestSuccess(response);
            }

            @Override
            public void onFail(int errorCode, String message) {

            }
        });
    }

    private void handlRequestSuccess(GiphyDataListResponse response) {
        int currentCount = dataSet.size();
        offset++ ;
        binding.progress.setVisibility(View.GONE);
        loadMoreModel.removeFromDataSet(dataSet, imageListAdapter);
        convertDataToRecyclerModel(response.data);


        imageListAdapter.setDataSet(dataSet);
        loadMoreModel.addToDataSetIfNecessary(dataSet, offset > 0);


        if (currentCount == 0) {
            imageListAdapter.notifyDataSetChanged();
        } else {
            imageListAdapter.notifyItemRangeInserted(currentCount, dataSet.size() - currentCount);
        }
    }

    private HashMap<String, String> loadMoreParams() {
        HashMap<String, String> param = new HashMap<>();
        param.put("offset", offset + "");
        return param;
    }

    private void convertDataToRecyclerModel(GiphyImage[] dataList) {
        for (int i = 0; i < dataList.length; i++) {
            dataSet.add(new GiphyRecyclerModel(dataList[i]));
        }

        Log.e("dataSet Length:" , dataSet.size()+"");
    }


}
