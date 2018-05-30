package com.open.templatebasic.fragment;

import android.support.v4.app.Fragment;

/**
 * Created on 2018/2/24.
 */


public enum HomeFragmentManager {
    INSTANCE;

    // fragment
    private FirstFragment firstFragment;
    private SecondFragment secondFragment;
    private ThirdFragment thirdFragment;
    private FourthFragment fourthFragment;

    public static final String KEY_FIRST = "first";
    public static final String KEY_SECOND = "second";
    public static final String KEY_THIRD = "third";
    public static final String KEY_FOURTH = "fourth";

    public void init() {
        firstFragment = FirstFragment.newInstance();
        secondFragment = SecondFragment.newInstance();
        thirdFragment = ThirdFragment.newInstance(KEY_THIRD);
        fourthFragment = FourthFragment.newInstance(KEY_FOURTH);
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
