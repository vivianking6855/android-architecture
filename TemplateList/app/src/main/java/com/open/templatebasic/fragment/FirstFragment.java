package com.open.templatebasic.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.open.templatebasic.R;
import com.open.templatebasic.adapter.FirstRecyclerAdapter;
import com.open.templatebasic.listener.BaseMessageEvent;
import com.open.templatebasic.listener.HomeMessageEvent;
import com.open.templatebasic.model.FirstModel;
import com.open.templatebasic.presenter.FirstPresenter;
import com.open.templatebasic.utils.Const;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * first fragment.
 */
public class FirstFragment extends Fragment {
    private final static String TAG = "FirstFragment";

    private FirstPresenter mPresenter;
    private TextView mHint;
    private FirstRecyclerAdapter mAdapter;

    public FirstFragment() {
    }

    public static FirstFragment newInstance() {
        return new FirstFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EventBus.getDefault().register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewHierarchy;
        viewHierarchy = inflater.inflate(R.layout.fragment_frist, container, false);

        initView(viewHierarchy);

        loadData();

        return viewHierarchy;
    }

    private void initView(View viewHierarchy) {
        RecyclerView recyclerView = (RecyclerView) viewHierarchy.findViewById(R.id.recycler_first);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new FirstRecyclerAdapter(getContext());
        recyclerView.setAdapter(mAdapter);

        mHint = (TextView) viewHierarchy.findViewById(R.id.tv_hint);
    }

    private void loadData() {
        mPresenter = new FirstPresenter();
        mPresenter.getData();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
        mPresenter.destroy();

        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onHomeMessageEvent(HomeMessageEvent event) {
        // if not current fragment data load
        if (event.moduleTag != Const.MODULE_FIRST) {
            return;
        }
        // deal with data load
        switch (event.loadStatus) {
            case BaseMessageEvent.STATUS_START:
                mHint.setText(getString(R.string.load_start));
                break;
            case BaseMessageEvent.STATUS_SUCCESS:
                mHint.setText(getString(R.string.load_success));
                mAdapter.setData((FirstModel) event.dataModel);
                break;
            case BaseMessageEvent.STATUS_FAIL:
                mHint.setText(getString(R.string.load_fail) + event.errorMsg);
                break;
            default:
                break;
        }
    }
}
