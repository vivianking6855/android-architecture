package com.clean.home;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.clean.R;
import com.clean.home.fragment.HomeListFragment;
import com.open.appbase.activity.BaseActivity;

/**
 * HomeActivity
 * IApkDisplayer as V in MVP
 */
public class HomeActivity extends BaseActivity {

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(android.R.id.content, HomeListFragment.newInstance())
                    .commitAllowingStateLoss();
        }
    }

    @Override
    protected void loadData() {

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

}
