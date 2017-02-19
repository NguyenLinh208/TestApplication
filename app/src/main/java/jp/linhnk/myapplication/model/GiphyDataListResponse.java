package jp.linhnk.myapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import jp.linhnk.myapplication.model.giphy.GiphyImage;


/**
 * Created by usr0200475 on 2017/02/17.
 * Copyright © 2017 GMO Media Inc. All rights reserved.
 */

public class GiphyDataListResponse implements Parcelable {
    public GiphyImage[] data;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedArray(this.data, flags);
    }

    public GiphyDataListResponse() {
    }

    protected GiphyDataListResponse(Parcel in) {
        this.data = in.createTypedArray(GiphyImage.CREATOR);
    }

    public static final Creator<GiphyDataListResponse> CREATOR = new Creator<GiphyDataListResponse>() {
        @Override
        public GiphyDataListResponse createFromParcel(Parcel source) {
            return new GiphyDataListResponse(source);
        }

        @Override
        public GiphyDataListResponse[] newArray(int size) {
            return new GiphyDataListResponse[size];
        }
    };
}
