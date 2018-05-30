package com.clean.home.fragment;

import android.support.v4.app.Fragment;

import com.clean.hot.fragment.HotFragment;
import com.clean.template.fragment.TemplateFragment;

/**
 * Created on 2018/2/24.
 */


public enum HomeFragmentManager {
    INSTANCE;

    // fragment
    private HotFragment firstFragment;
    private TemplateFragment secondFragment;
    private TemplateFragment thirdFragment;
    private TemplateFragment fourthFragment;

    public static final String KEY_FIRST = "first";
    public static final String KEY_SECOND = "second";
    public static final String KEY_THIRD = "third";
    public static final String KEY_FOURTH = "fourth";

    public void init() {
        firstFragment = HotFragment.newInstance();
        secondFragment = TemplateFragment.newInstance();
        thirdFragment = TemplateFragment.newInstance();
        fourthFragment = TemplateFragment.newInstance();
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
