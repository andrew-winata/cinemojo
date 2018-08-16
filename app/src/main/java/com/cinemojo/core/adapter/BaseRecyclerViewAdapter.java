package com.cinemojo.core.adapter;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerViewAdapter<T, VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH> {
    protected List<T> items;

    public BaseRecyclerViewAdapter(List<T> items) {
        this.items = items;
    }

    public void addItems(List<T> addItems) {
        for (T addItem : addItems) {
            this.addItem(addItem);
        }
    }

    public void addItem(T addItem) {
        this.items.add(addItem);
        notifyItemInserted(this.items.size() - 1);
    }

    public void changeItems(List<T> newItems) {
        if (this.items != null) {
            this.items.clear();
        }
        this.items.addAll(newItems);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }
}
