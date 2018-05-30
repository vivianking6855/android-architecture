package com.clean.template.presenter;

import android.support.v4.app.Fragment;

import com.clean.businesscommon.transaction.DataTransaction;
import com.clean.exception.ErrorMessageFactory;
import com.clean.template.listenter.ITemplateDisplayer;
import com.clean.template.model.TemplateModel;
import com.learn.data.common.ResultConst;
import com.learn.data.entity.TemplateEntity;
import com.learn.data.exception.DataException;
import com.learn.data.listener.ITemplateListener;
import com.learn.data.repository.TemplateRepo;
import com.learn.data.task.JobTask;
import com.open.appbase.fragment.BaseLazyFragment;
import com.open.appbase.presenter.BasePresenter;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * The type Home presenter.
 */
public class TemplatePresenter extends BasePresenter<ITemplateDisplayer> {
    // rx
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void fetchDataWithJobTask() {
        JobTask.THREAD_POOL_EXECUTOR.execute(() -> {
            try {
                TemplateEntity data = TemplateRepo.getData();
                (((Fragment) viewWeakRef.get()).getActivity()).runOnUiThread(() -> {
                    viewWeakRef.get().onDisplay(DataTransaction.transformData(data));
                    // set data load to avoid data load again
                    ((BaseLazyFragment) viewWeakRef.get()).setDataLoadCompleted(true);
                });
            } catch (DataException e) {
                (((Fragment) viewWeakRef.get()).getActivity()).runOnUiThread(() -> {
                    String error = ErrorMessageFactory.create((((Fragment) viewWeakRef.get()).getActivity()), e);
                    viewWeakRef.get().onDisplay(new TemplateModel(null, error));
                });
            }
        });
    }

    public void fetchDataWithListener() {
        TemplateRepo.getDataAsync(new ITemplateListener() {
                                      @Override
                                      public void onSuccess(TemplateEntity data) {
                                          (((Fragment) viewWeakRef.get()).getActivity()).runOnUiThread(() -> {
                                              viewWeakRef.get().onDisplay(DataTransaction.transformData(data));
                                              // set data load to avoid data load again
                                              ((BaseLazyFragment) viewWeakRef.get()).setDataLoadCompleted(true);
                                          });
                                      }

                                      @Override
                                      public void onError(Exception e) {
                                          (((Fragment) viewWeakRef.get()).getActivity()).runOnUiThread(() -> {
                                              String error = ErrorMessageFactory.create((((Fragment) viewWeakRef.get()).getActivity()), e);
                                              viewWeakRef.get().onDisplay(new TemplateModel(null, error));
                                          });
                                      }
                                  }
        );
    }

    /**
     * Fetch user.
     */
    public void fetchDataWithRx() {
        // load data
        Disposable data = Observable.fromCallable(TemplateRepo::getDataResult)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    if (result.state == ResultConst.DATA_SUCCESS) {
                        viewWeakRef.get().onDisplay(DataTransaction.transformData(result.infos));
                        // set data load to avoid data load again
                        ((BaseLazyFragment) viewWeakRef.get()).setDataLoadCompleted(true);
                    } else {
                        String error = ErrorMessageFactory.createWithErrorCode((((Fragment) viewWeakRef.get()).getActivity()),
                                result.state);
                        viewWeakRef.get().onDisplay(new TemplateModel(null, error));
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
