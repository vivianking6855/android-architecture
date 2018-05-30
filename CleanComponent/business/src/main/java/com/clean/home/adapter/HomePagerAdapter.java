package com.clean.home.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.clean.home.fragment.HomeFragmentManager;

import java.util.ArrayList;

public class HomePagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> mList; // data model

    public HomePagerAdapter(FragmentManager fm) {
        super(fm);

        mList = new ArrayList<>();

        // init data
        addFragment();
    }

    private void addFragment() {
        mList.add(HomeFragmentManager.INSTANCE.getFragment(HomeFragmentManager.KEY_FIRST));
        mList.add(HomeFragmentManager.INSTANCE.getFragment(HomeFragmentManager.KEY_SECOND));
        mList.add(HomeFragmentManager.INSTANCE.getFragment(HomeFragmentManager.KEY_THIRD));
        mList.add(HomeFragmentManager.INSTANCE.getFragment(HomeFragmentManager.KEY_FOURTH));
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }
}
