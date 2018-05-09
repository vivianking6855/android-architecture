package com.clean.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.clean.R;
import com.clean.model.UserModel;
import com.clean.presenter.HomePresenter;
import com.clean.businesscommon.listenter.IHomeDisplayer;
import com.clean.businesscommon.base.BaseMVPActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * HomeActivity
 * IHomeDisplayer as V in MVP
 */
public class HomeActivity extends BaseMVPActivity<IHomeDisplayer, HomePresenter> implements IHomeDisplayer {
    @BindView(android.R.id.title)
    TextView resultTV;

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
    }

    @Override
    protected void loadData() {
        mPresenter.fetchUser();
    }

    /**
     * Gets calling intent.
     *
     * @param context the context
     * @return the calling intent
     */
    public static Intent getCallingIntent(Context context) {
        return new Intent(context, HomeActivity.class);
    }

    @Override
    public void onDisplay(UserModel model) {
        if (model != null) {
            resultTV.setText(model.getUserId());
        }
    }


}
