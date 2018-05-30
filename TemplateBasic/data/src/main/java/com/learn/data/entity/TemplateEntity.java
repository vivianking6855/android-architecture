package com.learn.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class TemplateEntity implements Parcelable {
    public String id;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
    }

    public TemplateEntity(String id) {
        this.id = id;
    }

    protected TemplateEntity(Parcel in) {
        this.id = in.readString();
    }

    public static final Creator<TemplateEntity> CREATOR = new Creator<TemplateEntity>() {
        @Override
        public TemplateEntity createFromParcel(Parcel source) {
            return new TemplateEntity(source);
        }

        @Override
        public TemplateEntity[] newArray(int size) {
            return new TemplateEntity[size];
        }
    };
}
