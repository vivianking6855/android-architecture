package com.clean.home;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.widget.Toast;

import com.clean.R;
import com.clean.businesscommon.util.BottomNavigationViewHelper;
import com.clean.home.adapter.HomePagerAdapter;
import com.clean.home.fragment.HomeFragmentManager;
import com.open.appbase.activity.BasePermissionActivity;
import com.orhanobut.logger.Logger;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * HomeActivity
 * IApkDisplayer as V in MVP
 */
public class HomeActivity extends BasePermissionActivity {

    // bottom navigation
    @BindView(R.id.navigation)
    BottomNavigationView bottomNavigationView;
    private MenuItem currentItem; // current menu item

    // adapter
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    private final static boolean allowScroll = true;


    @BindArray(R.array.permission)
    String[] permissionStrs;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        HomeFragmentManager.INSTANCE.init();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ButterKnife.bind(this);

        // bottom navigation
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> dealBottomItemSelected(item.getItemId()));
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        initViewPager();
    }

    private void initViewPager() {
        // init home adapter
        HomePagerAdapter mPagerAdapter = new HomePagerAdapter(getSupportFragmentManager());

        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (currentItem != null) {
                    currentItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                currentItem = bottomNavigationView.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // disable viewpager scroll
        mViewPager.setOnTouchListener((v, event) -> !allowScroll);
    }

    @Override
    protected void loadData() {
        setPermissionAlterDialog(permissionStrs);

        currentItem = bottomNavigationView.getMenu().getItem(0);
    }

    private boolean dealBottomItemSelected(int itemId) {
        // current id pretreatment
        if (itemId == currentItem.getItemId()) {
            return false;
        }
        // reset current item
        currentItem = bottomNavigationView.getMenu().findItem(itemId);
        // set viewpager item
        switch (itemId) {
            case R.id.navigation_fist:
                mViewPager.setCurrentItem(0);
                return true;
            case R.id.navigation_second:
                mViewPager.setCurrentItem(1);
                return true;
            case R.id.navigation_third:
                mViewPager.setCurrentItem(2);
                return true;
            case R.id.navigation_fourth:
                mViewPager.setCurrentItem(3);
                return true;
        }

        return false;
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
        return new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
    }

    @Override
    protected void permissionGranted() {

    }

    @Override
    protected void permissionDeny(String[] notGranted) {
        Toast.makeText(HomeActivity.this, "Photo will not work", Toast.LENGTH_SHORT).show();
        //finish();
    }
}
