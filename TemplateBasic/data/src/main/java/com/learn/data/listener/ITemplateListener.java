package com.learn.data.listener;

import com.learn.data.entity.TemplateEntity;

public interface ITemplateListener {
    void onSuccess(TemplateEntity user);

    void onError(Exception e);
}
