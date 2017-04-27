package jp.linhnk.myapplication.model.uzabase;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

/**
 * Created by usr0200475 on 2017/03/08.
 * Copyright © 2017 GMO Media Inc. All rights reserved.
 */

@Root(name = "rss", strict = false)
public class Feed implements Serializable {
    @Element(name = "channel")
    private Channel mChannel;

    public Channel getmChannel() {
        return mChannel;
    }

    public Feed() {
    }

    public Feed(Channel mChannel) {
        this.mChannel = mChannel;
    }
}
