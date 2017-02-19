package jp.linhnk.myapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by usr0200475 on 2017/02/19.
 * Copyright © 2017 GMO Media Inc. All rights reserved.
 */

public abstract class HeaderFooterAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter {
    protected abstract VH onCreateItemViewHolder(ViewGroup parent, int viewType);

    protected abstract void onBindItemViewHolder(VH holder, int position);

    protected abstract int getAdapterItemCount();

    private class HeaderFooterHolder extends RecyclerView.ViewHolder {
        public HeaderFooterHolder(View itemView) {
            super(itemView);
        }
    }

    public static final int ITEM_VIEW_TYPE_HEADER = 10001;
    public static final int ITEM_VIEW_TYPE_FOOTER = 10002;
    private FooterGenerateListener footerGenerateListener;
    private FooterGenerateListener headerGenerateListener;

    public interface FooterGenerateListener {
        View getView(ViewGroup parent);
    }

    protected int getMainItemViewType(int position) {
        return 0;
    }

    public void setHeaderListener(FooterGenerateListener listener) {
        this.headerGenerateListener = listener;
    }

    public void setFooterListener(FooterGenerateListener listener) {
        this.footerGenerateListener = listener;
    }

    public void removeHeader() {
        if (this.headerGenerateListener == null) {
            return;
        }
        this.headerGenerateListener = null;
    }

    public void removeFooter() {
        if (this.footerGenerateListener == null) {
            return;
        }
        this.footerGenerateListener = null;
    }

    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ITEM_VIEW_TYPE_HEADER:
                return new HeaderFooterHolder(headerGenerateListener.getView(parent));
            case ITEM_VIEW_TYPE_FOOTER:
                return new HeaderFooterHolder(footerGenerateListener.getView(parent));
            default:
                return onCreateItemViewHolder(parent, viewType);
        }
    }

    @Override
    public final void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() != ITEM_VIEW_TYPE_HEADER && holder.getItemViewType() != ITEM_VIEW_TYPE_FOOTER) {
            onBindItemViewHolder((VH) holder, position - getHeadersCount());
        }
    }

    private int getHeadersCount() {
        return headerGenerateListener != null ? 1 : 0;
    }

    private int getFootersCount() {
        return footerGenerateListener != null ? 1 : 0;
    }

    @Override
    public final int getItemCount() {
        return getHeadersCount() + getFootersCount() + getAdapterItemCount();
    }

    @Override
    public final int getItemViewType(int position) {
        int numHeaders = getHeadersCount();
        if (position < numHeaders) {
            return ITEM_VIEW_TYPE_HEADER;
        }
        int adjPosition = position - numHeaders;
        int adapterCount = getAdapterItemCount();
        if (adjPosition < adapterCount) {
            return getMainItemViewType(adjPosition);
        }
        return ITEM_VIEW_TYPE_FOOTER;
    }
}
