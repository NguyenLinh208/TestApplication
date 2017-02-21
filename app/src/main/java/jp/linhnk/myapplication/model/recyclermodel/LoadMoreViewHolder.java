package jp.linhnk.myapplication.model.recyclermodel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import jp.linhnk.myapplication.R;
import jp.linhnk.myapplication.model.datamodel.RecyclerModel;

public class LoadMoreViewHolder extends RecyclerGridViewHolder {
    public LoadMoreViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindData(RecyclerModel model) {
        //ignore
    }

    public static RecyclerGridViewHolder create(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_load_more, parent, false);
        return new LoadMoreViewHolder(v);
    }
}
