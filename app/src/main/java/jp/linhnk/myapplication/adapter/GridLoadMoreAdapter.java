package jp.linhnk.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import jp.linhnk.myapplication.R;
import jp.linhnk.myapplication.model.datamodel.RecyclerModel;
import jp.linhnk.myapplication.model.recyclermodel.GiphyViewHolder;
import jp.linhnk.myapplication.model.datamodel.LoadMoreModel;
import jp.linhnk.myapplication.model.recyclermodel.LoadMoreViewHolder;
import jp.linhnk.myapplication.model.recyclermodel.RecyclerGridViewHolder;

/**
 * Created by usr0200475 on 2017/02/19.
 * Copyright © 2017 GMO Media Inc. All rights reserved.
 */

public class GridLoadMoreAdapter extends HeaderFooterAdapter<RecyclerGridViewHolder> {

    public void setDataSet(List<RecyclerModel> dataSet) {
        this.dataSet = dataSet;
    }

    private List<RecyclerModel> dataSet;

    public interface OnLoadMore {
        void onLoadMore();
    }
    private OnLoadMore onLoadMore;

    public void setOnLoadMore(OnLoadMore onLoadMore) {
        this.onLoadMore = onLoadMore;
    }

    @Override
    protected RecyclerGridViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        RecyclerModel.ItemViewType type = RecyclerModel.ItemViewType.values()[viewType];
        switch (type) {
            case LoadMore:
                return LoadMoreViewHolder.create(parent);
            default:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_cell_layout, parent, false);
                return new GiphyViewHolder(view);
        }
    }

    @Override
    protected void onBindItemViewHolder(RecyclerGridViewHolder holder, int position) {
        RecyclerModel model = dataSet.get(position);
        holder.bindData(model);

        if (model instanceof LoadMoreModel) {
            onLoadMore.onLoadMore();
        }
    }

    @Override
    protected int getMainItemViewType(int position) {
        return dataSet.get(position).getItemViewType().ordinal();
    }

    @Override
    protected int getAdapterItemCount() {
        if (dataSet != null) {
            return dataSet.size();
        } else {
            return 0;
        }
    }

    public int getSpanSize(int position) {
        int viewType = this.getItemViewType(position);
        if (viewType == ITEM_VIEW_TYPE_HEADER || viewType == ITEM_VIEW_TYPE_FOOTER) {
            return 2;
        }
        RecyclerModel.ItemViewType type = RecyclerModel.ItemViewType.values()[viewType];
        return type.isFullSpan ? 2 : 1;
    }
}
