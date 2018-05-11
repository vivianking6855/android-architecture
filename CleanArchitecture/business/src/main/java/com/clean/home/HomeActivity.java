package com.clean.home;

import android.Manifest;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.clean.R;
import com.clean.home.fragment.HomeListFragment;
import com.open.appbase.activity.BaseActivity;
import com.open.appbase.activity.BasePermissionActivity;

import java.security.Permission;
import java.security.PermissionCollection;

/**
 * HomeActivity
 * IApkDisplayer as V in MVP
 */
public class HomeActivity extends BasePermissionActivity {
    public static final String READ_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE;

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

    @Override
    protected String[] getPermissions() {
        return new String[]{READ_STORAGE};
    }

    @Override
    protected void permissionGranted() {

    }

    @Override
    protected void permissionDeny() {
        Toast.makeText(this,"Photo Read will not work", Toast.LENGTH_SHORT).show();
    }
}
