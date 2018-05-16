package com.clean.user.presenter;

import com.clean.businesscommon.transaction.DataTransaction;
import com.clean.user.listenter.IUserDisplayer;
import com.clean.user.model.UserModel;
import com.learn.data.repository.UserRepo;
import com.open.appbase.presenter.BasePresenter;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * The type Home presenter.
 */
public class UserPresenter extends BasePresenter<IUserDisplayer> {
    // rx
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    /**
     * Fetch user.
     */
    public void fetchUser() {
        // load data
        Disposable data = Observable.fromCallable(UserRepo::getUser)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(user -> {
                            viewWeakRef.get().onDisplay(DataTransaction.transformUser(user));
                        },
                        error -> {
                            viewWeakRef.get().onDisplay(new UserModel(null));
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
