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
     * Instantiates a new data model.
     *
     * @param id the data id
     */
    public TemplateModel(String id) {
        this.id = id;
        if (id != null) {
            status = SUCCESS;
        }
    }

    public TemplateModel(String id, String error) {
        this.id = id;
        this.error = error;
        if (id != null) {
            status = SUCCESS;
        }
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
