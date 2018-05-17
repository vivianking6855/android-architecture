package com.clean.user.presenter;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.clean.businesscommon.transaction.DataTransaction;
import com.clean.exception.ErrorMessageFactory;
import com.clean.user.listenter.IUserDisplayer;
import com.clean.user.model.UserModel;
import com.learn.data.common.ResultConst;
import com.learn.data.entity.UserEntity;
import com.learn.data.exception.DataResultFunc;
import com.learn.data.repository.UserRepo;
import com.open.appbase.presenter.BasePresenter;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * The type Home presenter.
 */
public class UserPresenter extends BasePresenter<IUserDisplayer> {
    // rx
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void fetchUserWithJobTask(){

    }

    public void fetchUserWithListener(){

    }

    /**
     * Fetch user.
     */
    public void fetchUserWithRx() {
        // load data
        Disposable data = Observable.fromCallable(UserRepo::getUserResult)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    if(result.state != ResultConst.DATA_SUCCESS){
                        String error = ErrorMessageFactory.createWithErrorCode((((Fragment) viewWeakRef.get()).getActivity()),
                                result.state);
                        viewWeakRef.get().onDisplay(new UserModel(null, error));
                    }else {
                        viewWeakRef.get().onDisplay(DataTransaction.transformUser(result.infos));
                    }

                });

        compositeDisposable.add(data);
    }

    @Override
    public void detachReference() {
        super.detachReference();

        // release rx
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }

}
