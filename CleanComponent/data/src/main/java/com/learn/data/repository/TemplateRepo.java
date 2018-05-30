package com.learn.data.repository;

import android.os.SystemClock;
import android.support.annotation.NonNull;

import com.learn.data.entity.ResultEntity;
import com.learn.data.entity.TemplateEntity;
import com.learn.data.exception.DataException;
import com.learn.data.listener.ITemplateListener;
import com.learn.data.task.JobTask;

import static com.learn.data.common.ResultConst.DATA_EMPTY;
import static com.learn.data.common.ResultConst.DATA_SUCCESS;

/**
 * template repo show you template usage
 */
@SuppressWarnings("unused")
public final class TemplateRepo {
    /**
     * Gets data async with Listener.
     *
     * @param listener the listener
     */
    public static void getDataAsync(@NonNull final ITemplateListener listener) {
        JobTask.THREAD_POOL_EXECUTOR.execute(new Runnable() {
            @Override
            public void run() {
                // consume task
                SystemClock.sleep(2000);

                TemplateEntity mock = getMockEntity();
                if (mock == null) {
                    listener.onError(new DataException(DATA_EMPTY));
                } else {
                    listener.onSuccess(mock);
                }
            }
        });
    }

    /**
     * Gets data sync, may throws DataException when user is null
     *
     * @return the data
     * @throws DataException the data exception
     */
    public static TemplateEntity getData() throws DataException {
        // consume task
        SystemClock.sleep(2000);

        TemplateEntity mock = getMockEntity();
        if (mock == null) {
            throw new DataException(DATA_EMPTY);
        }
        return mock;
    }

    /**
     * Gets data result with ResultEntity
     *
     * @return the user result
     */
    public static ResultEntity<TemplateEntity> getDataResult() {
        ResultEntity<TemplateEntity> result = new ResultEntity<>();
        result.state = DATA_SUCCESS;

        // consume task
        SystemClock.sleep(2000);

        TemplateEntity mock = getMockEntity();
        if (mock == null) {
            result.state = DATA_EMPTY;
        } else {
            result.infos = mock;
        }

        return result;
    }

    private static TemplateEntity getMockEntity() {
        String mockId = "mockId";
        return new TemplateEntity(mockId);
        //return null;
    }

}
