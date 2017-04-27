package jp.linhnk.myapplication;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import jp.linhnk.myapplication.adapter.FeedAdapter;
import jp.linhnk.myapplication.api.ApiObjectCallback;
import jp.linhnk.myapplication.api.UzabaseApi;
import jp.linhnk.myapplication.base.BaseActivity;
import jp.linhnk.myapplication.databinding.ActivityRecyclerListBinding;
import jp.linhnk.myapplication.model.datamodel.LoadMoreModel;
import jp.linhnk.myapplication.model.datamodel.RecyclerModel;
import jp.linhnk.myapplication.model.datamodel.UzabaseRecyclerModel;
import jp.linhnk.myapplication.model.uzabase.Feed;
import jp.linhnk.myapplication.model.uzabase.FeedItem;

/**
 * Created by usr0200475 on 2017/02/16
 * Copyright © 2017 GMO Media Inc. All rights reserved.
 */

public class UzabaseFeedListActivity extends BaseActivity {

    FeedAdapter feedAdapter;
    ActivityRecyclerListBinding binding;
    private List<FeedItem> feedItems = new ArrayList<>();
    protected List<RecyclerModel> dataSet = new ArrayList<>();
    protected LoadMoreModel loadMoreModel = new LoadMoreModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        feedAdapter = new FeedAdapter();
        configureLayout();
        request();
    }

    private void configureLayout() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recycler_list);
        binding.recyclerView.setHasFixedSize(true);
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(feedAdapter);
    }

    private void request() {
        UzabaseApi uzabaseApi = new UzabaseApi();
        uzabaseApi.start(new ApiObjectCallback<Feed>() {
            @Override
            public void onSuccess(Feed response) {
                feedItems = response.getmChannel().getFeedItemList();
                handleRequestSuccess(response.getmChannel().getFeedItemList());
            }

            @Override
            public void onFail(int errorCode, String message) {

            }
        });
    }

    private void handleRequestSuccess(List<FeedItem> response) {
        binding.progress.setVisibility(View.GONE);
        loadMoreModel.removeFromDataSet(dataSet, feedAdapter);
        convertDataToRecyclerModel();

        feedAdapter.setDataSet(dataSet);
        feedAdapter.notifyDataSetChanged();
    }


    private void convertDataToRecyclerModel() {
        dataSet.clear();
        for (FeedItem feedItem : feedItems) {
            dataSet.add(new UzabaseRecyclerModel(feedItem));
        }
        Log.e("dataSet Length:", dataSet.size() + "");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.select, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.option1) {
            binding.progress.setVisibility(View.VISIBLE);
            convertDataToRecyclerModel();
            changeView();
            return true;
        } else if (id == R.id.option2){
            binding.progress.setVisibility(View.VISIBLE);
            onlyNewsPickList();
            changeView();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void changeView() {
        feedAdapter.setDataSet(dataSet);
        feedAdapter.notifyDataSetChanged();
        binding.progress.setVisibility(View.GONE);
    }
    private void onlyNewsPickList() {
        dataSet.clear();
        for (FeedItem feedItem : feedItems) {
            if (feedItem.getDescription().contains("NewsPicks")) {
                dataSet.add(new UzabaseRecyclerModel(feedItem));
            }
        }
    }


}
