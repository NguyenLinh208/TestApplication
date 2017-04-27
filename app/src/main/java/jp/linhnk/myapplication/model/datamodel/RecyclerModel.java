package jp.linhnk.myapplication.model.datamodel;

/**
 * Created by usr0200475 on 2017/02/18.
 * Copyright © 2017 GMO Media Inc. All rights reserved.
 */

public abstract class RecyclerModel {

    public enum ItemViewType {
        Giphy(false),
        Uzabase(true),
        LoadMore(true),
        Unknow(false);

        //Layout manager用
        public final boolean isFullSpan;

        ItemViewType(boolean isFullSpan) {
            this.isFullSpan = isFullSpan;
        }
    }

    public abstract ItemViewType getItemViewType();
}
