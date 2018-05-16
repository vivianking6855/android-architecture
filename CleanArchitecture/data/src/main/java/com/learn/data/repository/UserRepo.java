package com.learn.data.repository;

import com.learn.data.entity.UserEntity;
import com.learn.data.exception.UserNotFoundException;
import com.learn.data.listener.IUserListener;

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
            if(mockId == null || mockId.isEmpty()){
                listener.onError(new UserNotFoundException());
            }else {
                listener.onSuccess(new UserEntity(mockId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static UserEntity getUser(){
        try {
            Thread.sleep(2000);
            String mockId = "mockId";
            if(mockId == null || mockId.isEmpty()){
                throw new UserNotFoundException();
            }
            return new UserEntity(mockId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
