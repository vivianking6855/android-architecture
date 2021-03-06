package com.clean.businesscommon.transaction;

import com.clean.template.model.TemplateModel;
import com.clean.user.model.UserModel;
import com.learn.data.entity.TemplateEntity;
import com.learn.data.entity.UserEntity;

/**
 * The type Data transaction.
 * transform entity to model
 */
public class DataTransaction {
    /**
     * Transform user model.
     *
     * @param entity the entity
     * @return the user model
     */
    public static UserModel transformUser(UserEntity entity) {
        if (entity == null) {
            return null;
        }
        UserModel model = new UserModel(entity.userId);
        return model;
    }

    /**
     * Transform data model.
     *
     * @param entity the entity
     * @return the user model
     */
    public static TemplateModel transformData(TemplateEntity entity) {
        if (entity == null) {
            return null;
        }
        TemplateModel model = new TemplateModel(entity.id);
        return model;
    }
}
