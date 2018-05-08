package com.open.templatebasic.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.open.templatebasic.R;
import com.open.templatebasic.model.FirstModel;

/**
 * Created by vivian on 2017/9/21.
 * recycler view third party template: https://github.com/captain-miao/RecyclerViewUtils
 */

public class FirstRecyclerAdapter extends RecyclerView.Adapter<FirstRecyclerAdapter.RecyclerViewHolder> {

    private Context mContext;
    private FirstModel mData;

    public FirstRecyclerAdapter(Context context) {
        mContext = context;
    }

    public void setData(FirstModel model) {
        mData = model;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.recycler_item_first, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.hint.setText(mData.titleArray.get(position));
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.titleArray.size();
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView hint;

        RecyclerViewHolder(View view) {
            super(view);
            hint = (TextView) view.findViewById(R.id.tv_content);
        }
    }
}
