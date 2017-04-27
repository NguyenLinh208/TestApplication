package jp.linhnk.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import jp.linhnk.myapplication.R;
import jp.linhnk.myapplication.model.datamodel.RecyclerModel;
import jp.linhnk.myapplication.model.recyclermodel.RecyclerGridViewHolder;
import jp.linhnk.myapplication.model.recyclermodel.UzabaseViewHolder;

/**
 * Created by usr0200475 on 2017/03/08.
 * Copyright © 2017 GMO Media Inc. All rights reserved.
 */

public class FeedAdapter extends HeaderFooterAdapter<RecyclerGridViewHolder> {
    public void setDataSet(List<RecyclerModel> dataSet) {
        this.dataSet = dataSet;
    }

    private List<RecyclerModel> dataSet;

    @Override
    protected RecyclerGridViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_uzabase_feed, parent, false);
        return new UzabaseViewHolder(view);
    }

    @Override
    protected void onBindItemViewHolder(RecyclerGridViewHolder holder, int position) {
        RecyclerModel model = dataSet.get(position);
        holder.bindData(model);
    }

    @Override
    protected int getAdapterItemCount() {
        if (dataSet != null) {
            return dataSet.size();
        } else {
            return 0;
        }
    }
}
