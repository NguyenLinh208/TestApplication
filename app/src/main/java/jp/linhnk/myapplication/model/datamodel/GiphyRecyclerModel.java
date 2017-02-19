package jp.linhnk.myapplication.model.datamodel;

import jp.linhnk.myapplication.model.giphy.GiphyImage;

/**
 * Created by usr0200475 on 2017/02/18.
 * Copyright © 2017 GMO Media Inc. All rights reserved.
 */

public class GiphyRecyclerModel extends RecyclerModel {

    public final GiphyImage giphyImage;

    public GiphyRecyclerModel(GiphyImage giphyImage) {
        this.giphyImage = giphyImage;
    }

    @Override
    public ItemViewType getItemViewType() {
        return ItemViewType.Giphy;
    }
}
