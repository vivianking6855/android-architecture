package com.open.templatebasic.presenter;

import android.util.Log;

import com.open.templatebasic.listener.BaseMessageEvent;
import com.open.templatebasic.listener.HomeMessageEvent;
import com.open.templatebasic.model.FirstDataApi;
import com.open.templatebasic.utils.Const;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by vivian on 2017/9/26.
 * MVP's presenter for First Fragment
 */

public class FirstPresenter {
    private static final String TAG = "SecondPresenter";
    // rx
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void getData() {
        Log.d(TAG, "getData");
        // load start notify
        EventBus.getDefault().post(new HomeMessageEvent(Const.MODULE_FIRST)
                .setLoadStatus(BaseMessageEvent.STATUS_START));

        // load data
        Disposable data = Observable.fromCallable(FirstDataApi::getData)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(model -> {
                            Log.d(TAG, "getData Success : " + model.toString());
                            EventBus.getDefault().post(new HomeMessageEvent(Const.MODULE_FIRST)
                                    .setLoadStatus(BaseMessageEvent.STATUS_SUCCESS)
                                    .setDataModel(model));
                        },
                        error -> {
                            Log.w(TAG, "getData error:", error);
                            EventBus.getDefault().post(new HomeMessageEvent(Const.MODULE_FIRST)
                                    .setLoadStatus(BaseMessageEvent.STATUS_FAIL)
                                    .setErrorMsg(error.toString()));
                        });

        compositeDisposable.add(data);
    }

    public void destroy() {
        // to avoid okhttp leak
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }


}
