package jp.linhnk.myapplication.model.uzabase;

import android.databinding.BaseObservable;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Created by usr0200475 on 2017/03/08.
 * Copyright © 2017 GMO Media Inc. All rights reserved.
 */

@Root(name = "enclosure")
public class Enclosure extends BaseObservable {

    @Attribute(name = "url")
    private String url;

    @Attribute(name = "length")
    private long length;

    @Attribute(name = "type")
    private String type;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
