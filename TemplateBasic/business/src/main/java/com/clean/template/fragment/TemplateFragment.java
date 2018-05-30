package com.clean.template.fragment;

import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import com.clean.R;
import com.clean.template.listenter.ITemplateDisplayer;
import com.clean.template.model.TemplateModel;
import com.clean.template.presenter.TemplatePresenter;
import com.open.appbase.fragment.BaseMVPLazyFragment;
import com.orhanobut.logger.Logger;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Template Fragment show you the template usage.
 */
public class TemplateFragment extends BaseMVPLazyFragment<ITemplateDisplayer, TemplatePresenter> implements ITemplateDisplayer {
    private Reference<FragmentActivity> mActivityRef;

    private Unbinder unbinder;

    @BindView(android.R.id.title)
    TextView titleTV;

    public static TemplateFragment newInstance() {
        return new TemplateFragment();
    }

    @Override
    protected TemplatePresenter createPresenter() {
        return new TemplatePresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_template;
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
    }

    @Override
    protected void loadData() {
        // mPresenter.fetchDataWithRx();
        //mPresenter.fetchDataWithJobTask();
        mPresenter.fetchDataWithListener();
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
    public void onDisplay(TemplateModel data) {
        if (data != null) {
            titleTV.setText("Template: " + data.toString());
        }
    }
}
