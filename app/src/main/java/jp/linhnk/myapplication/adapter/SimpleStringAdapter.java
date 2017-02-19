package jp.linhnk.myapplication.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.databinding.library.baseAdapters.BR;
import java.util.ArrayList;
import java.util.List;
import jp.linhnk.myapplication.R;
import jp.linhnk.myapplication.model.ListItem;

/**
 * Adapter used to map a String to a text view.
 */
public class SimpleStringAdapter extends RecyclerView.Adapter<SimpleStringAdapter.ViewHolder> {

    private final Context mContext;
    private List<ListItem> mItems = new ArrayList<>();

    public SimpleStringAdapter(Context context) {
        mContext = context;
    }

    public void setListItem(List<ListItem> listItem) {
        mItems = listItem;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_string_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.getViewDataBinding().setVariable(BR.listItem, getItem(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public ListItem getItem(int position) {
        return mItems.get(position);
    }

    //Implement ViewHolder Class
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding viewDataBinding;

        public ViewHolder(View view) {
            super(view);
            viewDataBinding = DataBindingUtil.bind(view);
        }

        public ViewDataBinding getViewDataBinding() {
            return viewDataBinding;
        }
    }

}
