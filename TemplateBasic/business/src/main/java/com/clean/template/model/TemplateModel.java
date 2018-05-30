package com.clean.template.model;

import com.alibaba.fastjson.JSON;

/**
 * template model show you the template usage.
 */
public class TemplateModel {
    public final String id;
    public String coverUrl;
    public String fullName;
    public String description;

    // load status
    public final static int SUCCESS = 1;
    public final static int FAIL = 0;
    public int status = 0;
    public String error;

    /**
     * Instantiates a new User model.
     *
     * @param userId the user id
     */
    public TemplateModel(String userId) {
        this.id = userId;
        if (userId != null) {
            status = SUCCESS;
        }
    }

    public TemplateModel(String userId, String error) {
        this.id = userId;
        this.error = error;
        if (userId != null) {
            status = SUCCESS;
        }
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
