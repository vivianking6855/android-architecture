package com.clean.presenter;

import com.clean.businesscommon.base.BasePresenter;
import com.clean.businesscommon.listenter.IHomeDisplayer;
import com.clean.businesscommon.transaction.DataTransaction;
import com.learn.data.repository.UserProvider;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * The type Home presenter.
 */
public class HomePresenter extends BasePresenter<IHomeDisplayer> {
    // rx
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    /**
     * Fetch user.
     */
    public void fetchUser() {
        // load data
        Disposable data = Observable.fromCallable(UserProvider::getUser)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user -> {
                            mOuterWeakRef.get().onDisplay(DataTransaction.transform(user));
                        },
                        error -> {
                            // TODO
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
