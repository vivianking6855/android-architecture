package com.learn.data.repository;

import android.os.SystemClock;

import com.learn.data.entity.HotEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vivian on 2017/9/26.
 * First Fragment Data API, will load data and deal with all logic here
 */

public class HotRepo {

    public static List<HotEntity> getData() {
        // do your consume work here
        SystemClock.sleep(2000);

        // add test data
        List<HotEntity> list = new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++) {
            HotEntity model = new HotEntity("" + (char) i);
            list.add(model);
        }

        return list;
    }
}
