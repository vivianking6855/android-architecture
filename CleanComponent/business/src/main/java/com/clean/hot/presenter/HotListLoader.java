package com.clean.hot.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import com.learn.data.entity.HotEntity;
import com.learn.data.repository.HotRepo;
import com.orhanobut.logger.Logger;

import java.util.List;

public class HotListLoader extends AsyncTaskLoader<List<HotEntity>> {

    HotListLoader(@NonNull Context context) {
        super(context);
    }

    @Nullable
    @Override
    public List<HotEntity> loadInBackground() {
        Logger.d("loadInBackground");
        return HotRepo.getData();
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    protected void onReset() {
        stopLoading();
    }
}
