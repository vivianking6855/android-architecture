package com.open.templatebasic.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.open.templatebasic.fragment.HomeFragmentManager;

import java.util.ArrayList;

/**
 * Created on 2017/8/31.
 */

public class HomePagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> mList;

    public HomePagerAdapter(FragmentManager fm) {
        super(fm);

        mList = new ArrayList<>();

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
