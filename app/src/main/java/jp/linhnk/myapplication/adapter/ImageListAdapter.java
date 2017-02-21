package jp.linhnk.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import jp.linhnk.myapplication.R;
import jp.linhnk.myapplication.model.giphy.GiphyImage;
import jp.linhnk.myapplication.model.recyclermodel.GiphyViewHolder;

/**
 * Created by usr0200475 on 2017/02/18.
 * Copyright © 2017 GMO Media Inc. All rights reserved.
 */

public class ImageListAdapter extends RecyclerView.Adapter<GiphyViewHolder> {

    private Context context;

    public void setDataList(GiphyImage[] dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    private GiphyImage[] dataList;

    public ImageListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public GiphyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_cell_layout, parent, false);
        return new GiphyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GiphyViewHolder holder, int position) {
        holder.populate(getItem(position));

    }

    private GiphyImage getItem(int position) {
        return dataList[position];
    }

    @Override
    public int getItemCount() {
        return dataList.length;
    }


}
