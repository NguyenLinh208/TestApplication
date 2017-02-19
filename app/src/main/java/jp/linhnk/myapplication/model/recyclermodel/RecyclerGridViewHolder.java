package jp.linhnk.myapplication.model.recyclermodel;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import jp.linhnk.myapplication.model.datamodel.RecyclerModel;

/**
 * Created by usr0200475 on 2017/02/18.
 * Copyright © 2017 GMO Media Inc. All rights reserved.
 */

public abstract class RecyclerGridViewHolder extends RecyclerView.ViewHolder {
    public RecyclerGridViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindData(RecyclerModel recyclerModel);
}
