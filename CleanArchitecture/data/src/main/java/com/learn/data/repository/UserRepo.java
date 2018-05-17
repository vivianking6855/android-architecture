package com.learn.data.repository;

import com.learn.data.entity.ResultEntity;
import com.learn.data.entity.UserEntity;
import com.learn.data.exception.DataException;
import com.learn.data.listener.IUserListener;

import static com.learn.data.common.ResultConst.DATA_ERROR;
import static com.learn.data.common.ResultConst.DATA_NO_USER;
import static com.learn.data.common.ResultConst.DATA_SUCCESS;

/**
 * The type User provider.
 */
public final class UserRepo {
    /**
     * Gets user.
     *
     * @return the user
     */
    public static void getUserAsync(IUserListener listener) {
        if (listener == null) {
            return;
        }
        try {
            Thread.sleep(2000);

            String mockId = "mockId";
            if (mockId == null || mockId.isEmpty()) {
                listener.onError(new DataException(DATA_NO_USER));
            } else {
                listener.onSuccess(new UserEntity(mockId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static UserEntity getUser() throws DataException {
        try {
            Thread.sleep(2000);
            String mockId = null;//"mockId";
            if (mockId == null || mockId.isEmpty()) {
                throw new DataException(DATA_NO_USER);
            }
            return new UserEntity(mockId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ResultEntity<UserEntity> getUserResult() {
        ResultEntity<UserEntity> result = new ResultEntity<>();
        result.state = DATA_SUCCESS;

        try {
            Thread.sleep(2000);
            String mockId = null;//"mockId";
            if (mockId == null || mockId.isEmpty()) {
                result.state = DATA_NO_USER;
            } else {
                result.infos = new UserEntity(mockId);
            }
        } catch (Exception e) {
            result.state = DATA_ERROR;
        }

        return result;
    }


}
