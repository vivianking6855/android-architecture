package com.clean.home.fragment;

import android.support.v4.app.Fragment;

import com.clean.apklist.fragment.ApkListFragment;
import com.clean.photo.fragment.PhotoListFragment;
import com.clean.user.fragment.UserFragment;

/**
 * Created on 2018/2/24.
 */


public enum HomeFragmentManager {
    INSTANCE;

    // fragment
    private ApkListFragment firstFragment;
    private PhotoListFragment secondFragment;
    private UserFragment thirdFragment;
    private UserFragment fourthFragment;

    public static final String KEY_FIRST = "first";
    public static final String KEY_SECOND = "second";
    public static final String KEY_THIRD = "third";
    public static final String KEY_FOURTH = "fourth";

    public void init() {
        firstFragment = ApkListFragment.newInstance();
        secondFragment = PhotoListFragment.newInstance();
        thirdFragment = UserFragment.newInstance();
        fourthFragment = UserFragment.newInstance();
    }

    public Fragment getFragment(String key) {
        switch (key) {
            case KEY_FIRST:
                return firstFragment;
            case KEY_SECOND:
                return secondFragment;
            case KEY_THIRD:
                return thirdFragment;
            case KEY_FOURTH:
                return fourthFragment;
            default:
                return null;
        }
    }
}
