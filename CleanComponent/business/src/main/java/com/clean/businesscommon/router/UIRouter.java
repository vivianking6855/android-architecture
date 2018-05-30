package com.clean.businesscommon.router;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.IntDef;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.clean.home.HomeActivity;
import com.clean.hot.fragment.HotFragment;
import com.clean.template.fragment.TemplateFragment;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Class used to navigate through the several UI in application.
 */
public final class UIRouter {

    /**
     * open home HomeActivity
     *
     * @param context A Context needed to open the destiny activity.
     */
    public static void gotoHome(Context context) {
        if (context != null) {
            Intent intentToLaunch = HomeActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }

    public static void gotoFragment(FragmentActivity activity, @UIType.Type int position) {
        if (activity == null) {
            return;
        }

        Fragment fragment = null;
        switch (position) {
            case UIType.HOT:
                fragment = HotFragment.newInstance();
                break;
            case UIType.TEMPLATE:
                fragment = TemplateFragment.newInstance();
                break;
            default:
                break;
        }

        if (fragment == null) {
            return;
        }

        activity.getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, fragment)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    public static class UIType {
        public static final int HOT = 0;
        public static final int TEMPLATE = 1;

        @IntDef({HOT, TEMPLATE})
        @Retention(RetentionPolicy.SOURCE)
        public @interface Type {
        }
    }

}
