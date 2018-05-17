package com.learn.data.exception;

import com.learn.data.entity.ResultEntity;

import io.reactivex.functions.Function;

public class DataResultFunc<T> implements Function<ResultEntity<T>, T> {

    @Override
    public T apply(ResultEntity<T> tResultEntity) throws Exception {
        return tResultEntity.infos;
    }
}
