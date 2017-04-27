package jp.linhnk.myapplication.model.recyclermodel;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;

import jp.linhnk.myapplication.BR;
import jp.linhnk.myapplication.customview.ImageAutoScale;
import jp.linhnk.myapplication.model.datamodel.RecyclerModel;
import jp.linhnk.myapplication.model.datamodel.UzabaseRecyclerModel;
import jp.linhnk.myapplication.model.uzabase.FeedItem;

/**
 * Created by usr0200475 on 2017/03/08.
 * Copyright © 2017 GMO Media Inc. All rights reserved.
 */

public class UzabaseViewHolder extends RecyclerGridViewHolder {
    private ViewDataBinding viewDataBinding;

    public UzabaseViewHolder(View itemView) {
        super(itemView);
        viewDataBinding = DataBindingUtil.bind(itemView);
    }

    @Override
    public void bindData(RecyclerModel recyclerModel) {
        FeedItem feedItem = ((UzabaseRecyclerModel) recyclerModel).feedItem;
        viewDataBinding.setVariable(BR.feed, feedItem);
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(final ImageAutoScale view, final String url) {
        Glide.with(view.getContext()).load(url).asBitmap().into(view);
    }
}
