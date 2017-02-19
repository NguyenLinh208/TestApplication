package jp.linhnk.myapplication.model.giphy;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import jp.linhnk.myapplication.BR;

/**
 * Created by usr0200475 on 2017/02/16.
 * Copyright © 2017 GMO Media Inc. All rights reserved.
 */

public class GiphyImage extends BaseObservable implements Parcelable {

    public String id;
    public String slug;
    public String bitlyGifUrl;
    public String bitlyUrl;
    public String trendingDatetime;
    public Images images;

    @Bindable
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Bindable
    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    @Bindable
    public String getBitlyGifUrl() {
        return bitlyGifUrl;
    }

    public void setBitlyGifUrl(String bitlyGifUrl) {
        this.bitlyGifUrl = bitlyGifUrl;
    }

    @Bindable
    public String getBitlyUrl() {
        return bitlyUrl;
    }

    public void setBitlyUrl(String bitlyUrl) {
        this.bitlyUrl = bitlyUrl;
    }

    @Bindable
    public String getTrendingDatetime() {
        return trendingDatetime;
    }

    public void setTrendingDatetime(String trendingDatetime) {
        this.trendingDatetime = trendingDatetime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.slug);
        dest.writeString(this.bitlyGifUrl);
        dest.writeString(this.bitlyUrl);
        dest.writeString(this.trendingDatetime);
    }

    public GiphyImage() {
    }

    protected GiphyImage(Parcel in) {
        this.id = in.readString();
        this.slug = in.readString();
        this.bitlyGifUrl = in.readString();
        this.bitlyUrl = in.readString();
        this.trendingDatetime = in.readString();
    }

    public static final Creator<GiphyImage> CREATOR = new Creator<GiphyImage>() {
        @Override
        public GiphyImage createFromParcel(Parcel source) {
            return new GiphyImage(source);
        }

        @Override
        public GiphyImage[] newArray(int size) {
            return new GiphyImage[size];
        }
    };

    public String toString() {
        return "id: " + id + "url" + getBitlyUrl();
    }
}
