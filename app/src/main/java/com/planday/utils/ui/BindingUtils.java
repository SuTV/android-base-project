package com.planday.utils.ui;

import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.util.List;

import com.planday.mvvm.MyApplication;
import com.planday.mvvm.view.adapter.BaseRecyclerViewAdapter;

public class BindingUtils {
    @BindingConversion
    public static String convertIdToString(int stringId) {
        return MyApplication.context().getString(stringId);
    }

    @BindingAdapter(value = {"android:src", "placeHolder"})
    public static void setImageUrl(ImageView view, String url, Drawable placeHolder) {
        RequestCreator requestCreator = Picasso.with(view.getContext()).load(url);
        requestCreator.placeholder(placeHolder);
        requestCreator.into(view);
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter("items")
    public static <T> void setItems(RecyclerView recyclerView, List<T> items) {
        BaseRecyclerViewAdapter<T> adapter = (BaseRecyclerViewAdapter<T>) recyclerView.getAdapter();
        if (adapter != null) adapter.setItems(items);
    }
}
