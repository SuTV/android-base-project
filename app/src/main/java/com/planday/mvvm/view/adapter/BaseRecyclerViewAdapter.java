package com.planday.mvvm.view.adapter;

import android.support.v7.widget.RecyclerView;

import java.util.List;

public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public abstract void setItems(List<T> places);
}
