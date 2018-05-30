package com.clean.hot.fragment;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.clean.R;
import com.clean.hot.adapter.HotListAdapter;
import com.clean.hot.listenter.IHotDisplayer;
import com.clean.hot.presenter.HotPresenter;
import com.open.appbase.fragment.BaseMVPLazyFragment;
import com.orhanobut.logger.Logger;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Apk List Fragment
 */
public class HotFragment extends BaseMVPLazyFragment<IHotDisplayer, HotPresenter> implements IHotDisplayer {
    private Reference<FragmentActivity> mActivityRef;

    private Unbinder unbinder;
    private HotListAdapter mAdapter;

    @BindView(android.R.id.list)
    RecyclerView recyclerView;
    @BindView(android.R.id.title)
    TextView statusTV;

    public static HotFragment newInstance() {
        return new HotFragment();
    }

    @Override
    protected HotPresenter createPresenter() {
        return new HotPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initData() {
        mActivityRef = new WeakReference<>(getActivity());
    }

    @Override
    protected void initViews(View view) {
        if (getActivity() == null) {
            return;
        }

        unbinder = ButterKnife.bind(this, view);

        recyclerView.setLayoutManager(new LinearLayoutManager(mActivityRef.get()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(
                getActivity(), DividerItemDecoration.VERTICAL));
        mAdapter = new HotListAdapter(mActivityRef.get());
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void loadData() {
        mPresenter.setAdapter(mAdapter);
        mPresenter.startLoader();
    }

    @Override
    protected boolean isKeepRootView() {
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        safeDestroy();
    }

    private void safeDestroy() {
        try {
            unbinder.unbind();
        } catch (Exception ex) {
            Logger.w("safeDestroy ex", ex);
        }
    }

    @Override
    public void onDisplay(String msg) {
        statusTV.setText(msg);
    }
}
