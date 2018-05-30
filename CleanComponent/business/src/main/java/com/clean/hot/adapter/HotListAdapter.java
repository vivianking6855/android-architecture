package com.clean.hot.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.clean.R;
import com.learn.data.entity.HotEntity;
import com.open.appbase.adapter.recyclerview.BaseRecyclerListAdapter;

public class HotListAdapter extends BaseRecyclerListAdapter<HotEntity, HotListAdapter.DataViewHolder> {
    // global LayoutInflater to void multi-get in onCreateViewHolder
    private final LayoutInflater mLayoutInflater;

    public HotListAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.hot_recycler_item, parent, false);
        return new HotListAdapter.DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        if (mData == null || position >= mData.size()) {
            return;
        }
        holder.tv.setText(mData.get(position).title);
    }

    class DataViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        DataViewHolder(View view) {
            super(view);
            tv = view.findViewById(android.R.id.title);
        }
    }
}
