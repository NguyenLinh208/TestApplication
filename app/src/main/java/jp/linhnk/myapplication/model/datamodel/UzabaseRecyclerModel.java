package jp.linhnk.myapplication.model.datamodel;

import jp.linhnk.myapplication.model.uzabase.FeedItem;

/**
 * Created by usr0200475 on 2017/03/08.
 * Copyright © 2017 GMO Media Inc. All rights reserved.
 */

public class UzabaseRecyclerModel extends RecyclerModel {

    public final FeedItem feedItem;

    public UzabaseRecyclerModel(FeedItem feedItem) {
        this.feedItem = feedItem;
    }

    @Override
    public ItemViewType getItemViewType() {
        return ItemViewType.Uzabase;
    }
}
