package jp.linhnk.myapplication.model.datamodel;

import android.support.v7.widget.RecyclerView;

import java.util.List;

import jp.linhnk.myapplication.model.datamodel.RecyclerModel;

public class LoadMoreModel extends RecyclerModel {
    private boolean isAdded;

    @Override
    public ItemViewType getItemViewType() {
        return ItemViewType.LoadMore;
    }

    public void addToDataSetIfNecessary(List<RecyclerModel> dataSet, boolean condition) {
        if (condition && !isAdded) {
            isAdded = true;
            dataSet.add(this);
        }
    }

    public void removeFromDataSet(List<RecyclerModel> dataSet, RecyclerView.Adapter adapter) {
        if (isAdded) {
            isAdded = false;
            int position = dataSet.size() - 1;
            dataSet.remove(position);
            adapter.notifyItemRemoved(position);
        }
    }

    public void hasBeenRemovedFromDataSet() {
        isAdded = false;
    }
}
