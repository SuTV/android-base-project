package com.planday.mvvm.view.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import com.planday.mvvm.BR;
import com.planday.mvvm.R;
import com.planday.mvvm.databinding.ItemPlaceBinding;
import com.planday.biz.model.Place;

public class PlacesAdapter extends BaseRecyclerViewAdapter<Place> {
    private Context mContext;
    private List<Place> mPlaces;

    public PlacesAdapter(Context context) {
        mContext = context;
        mPlaces = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemPlaceBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.item_place, parent, false);
        return new PlaceViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        PlaceViewHolder holder = (PlaceViewHolder) viewHolder;
        Place place = mPlaces.get(position);
        holder.getBinding().setVariable(BR.place, place);
        holder.getBinding().executePendingBindings();
        holder.itemView.setOnClickListener(v -> {
        });
    }

    @Override
    public int getItemCount() {
        return mPlaces.size();
    }

    // this view holder hold the view of one particular card
    public static class PlaceViewHolder extends RecyclerView.ViewHolder {
        private ItemPlaceBinding binding;

        public PlaceViewHolder(View rowView) {
            super(rowView);
            binding = DataBindingUtil.bind(rowView);
        }

        public ItemPlaceBinding getBinding() {
            return binding;
        }
    }

    /**
     * Update the list item
     */
    public void setItems(List<Place> places) {
        mPlaces = places;
        notifyDataSetChanged();
    }
}
