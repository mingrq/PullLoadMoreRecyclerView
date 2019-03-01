package com.ming.pullloadmorerecyclerview;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by cl on 2018/5/3.
 */

public class StaggeredGridImageDataBean implements Serializable {
    private String url;
    private int width;
    private int height;

    public StaggeredGridImageDataBean() {
    }

    public StaggeredGridImageDataBean(String url, int width, int height) {
        this.url = url;
        this.width = width;
        this.height = height;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    protected StaggeredGridImageDataBean(Parcel in) {
        this.url = in.readString();
        this.width = in.readInt();
        this.height = in.readInt();
    }

    public static final Parcelable.Creator<StaggeredGridImageDataBean> CREATOR = new Parcelable.Creator<StaggeredGridImageDataBean>() {
        @Override
        public StaggeredGridImageDataBean createFromParcel(Parcel source) {
            return new StaggeredGridImageDataBean(source);
        }

        @Override
        public StaggeredGridImageDataBean[] newArray(int size) {
            return new StaggeredGridImageDataBean[size];
        }
    };
}
