package com.clean.businesscommon.transaction;

import com.clean.template.model.TemplateModel;
import com.learn.data.entity.TemplateEntity;

/**
 * The type Data transaction.
 * transform entity to model
 */
public class DataTransaction {
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
