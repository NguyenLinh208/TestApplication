package jp.linhnk.myapplication.model.recyclermodel;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;

import jp.linhnk.myapplication.BR;
import jp.linhnk.myapplication.customview.ImageAutoScale;
import jp.linhnk.myapplication.model.datamodel.GiphyRecyclerModel;
import jp.linhnk.myapplication.model.datamodel.RecyclerModel;
import jp.linhnk.myapplication.model.giphy.GiphyImage;

/**
 * Created by usr0200475 on 2017/02/19.
 * Copyright © 2017 GMO Media Inc. All rights reserved.
 */


public class GiphyViewHolder extends RecyclerGridViewHolder {
    private ViewDataBinding viewDataBinding;

    public GiphyViewHolder(View itemView) {
        super(itemView);
        viewDataBinding = DataBindingUtil.bind(itemView);
    }

    @Override
    public void bindData(RecyclerModel recyclerModel) {
        GiphyImage giphyImage = ((GiphyRecyclerModel) recyclerModel).giphyImage;
        viewDataBinding.setVariable(BR.original, giphyImage.images.original);
    }

    public void populate(GiphyImage giphyImage) {
        viewDataBinding.setVariable(BR.original, giphyImage.images.original);
        viewDataBinding.executePendingBindings();
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(final ImageAutoScale view, final String url) {
        Glide.with(view.getContext()).load(url).asBitmap().into(view);
    }
}
