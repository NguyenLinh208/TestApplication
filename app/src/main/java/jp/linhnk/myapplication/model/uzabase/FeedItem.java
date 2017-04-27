package jp.linhnk.myapplication.model.uzabase;

import android.databinding.BaseObservable;
import android.text.Html;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

/**
 * Created by usr0200475 on 2017/03/07.
 * Copyright © 2017 GMO Media Inc. All rights reserved.
 */

@Root(name = "item", strict = false)
public class FeedItem extends BaseObservable implements Serializable {
    @Element(name = "title")
    private String title;

    @Element(name = "description")
    private String description;

    @Element(name = "pubDate")
    private String pubDate;

    @Element(name = "guid")
    private String guid;

    @Element(name = "link")
    private String link;

    @Element(name = "enclosure")
    private Enclosure enclosure;

    public FeedItem() {
    }

    public FeedItem(String title, String description, String pubDate, String link, Enclosure enclosure) {
        this.title = title;
        this.description = description;
        this.pubDate = pubDate;
        this.link = link;
        this.enclosure = enclosure;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description.replaceAll("<.+?>", "");
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Enclosure getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(Enclosure enclosure) {
        this.enclosure = enclosure;
    }


}
