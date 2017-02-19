package jp.linhnk.myapplication.adapter;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;

import jp.linhnk.myapplication.BR;
import jp.linhnk.myapplication.R;
import jp.linhnk.myapplication.customview.ImageAutoScale;
import jp.linhnk.myapplication.model.giphy.GiphyImage;
import jp.linhnk.myapplication.model.datamodel.RecyclerModel;
import jp.linhnk.myapplication.model.recyclermodel.RecyclerGridViewHolder;

/**
 * Created by usr0200475 on 2017/02/18.
 * Copyright © 2017 GMO Media Inc. All rights reserved.
 */

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.GiphyViewHolder> {

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
        holder.getViewDataBinding().setVariable(BR.original, getItem(position).images.original);
        holder.getViewDataBinding().executePendingBindings();
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(final ImageAutoScale view, final String url) {
        Log.e("url:", url);
        Glide.with(view.getContext()).load(url).asBitmap().into(view);
    }

    private GiphyImage getItem(int position) {
        return dataList[position];
    }

    @Override
    public int getItemCount() {
        return dataList.length;
    }

    public class GiphyViewHolder extends RecyclerGridViewHolder {
        private ViewDataBinding viewDataBinding;

        public GiphyViewHolder(View itemView) {
            super(itemView);
            viewDataBinding = DataBindingUtil.bind(itemView);
        }

        @Override
        public void bindData(RecyclerModel recyclerModel) {

        }

        public ViewDataBinding getViewDataBinding() {
            return viewDataBinding;
        }
    }
}
