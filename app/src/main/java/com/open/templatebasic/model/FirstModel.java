package com.open.templatebasic.model;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Created by vivian on 2017/9/26.
 * model for first Fragment
 */

public class FirstModel implements IDataModel {
    public List<String> titleArray;

    @Override
    public String toString() {
        return StringUtils.join(titleArray.toArray(), ",");
    }
}
