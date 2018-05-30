package com.open.templatebasic.model;

import android.os.SystemClock;

import java.util.ArrayList;

/**
 * Created by vivian on 2017/9/26.
 * First Fragment Data API, will load data and deal with all logic here
 */

public class FirstDataApi {

    public static FirstModel getData() {
        // do your consume work here
        SystemClock.sleep(2000);

        // add test data
        FirstModel model = new FirstModel();
        model.titleArray = new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++) {
            model.titleArray.add("" + (char) i);
        }

        return model;
    }
}
