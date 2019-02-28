package com.ming.pullloadmorerecyclerview_lib;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cl on 2018/5/3.
 */

public class GirlItemData implements Parcelable {
    private String url;
    private int width;
    private int height;

    public GirlItemData() {
    }

    public GirlItemData(String title, String url, String id, int width, int height, String subtype) {
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeInt(this.width);
        dest.writeInt(this.height);
    }

    protected GirlItemData(Parcel in) {
        this.url = in.readString();
        this.width = in.readInt();
        this.height = in.readInt();
    }

    public static final Parcelable.Creator<GirlItemData> CREATOR = new Parcelable.Creator<GirlItemData>() {
        @Override
        public GirlItemData createFromParcel(Parcel source) {
            return new GirlItemData(source);
        }

        @Override
        public GirlItemData[] newArray(int size) {
            return new GirlItemData[size];
        }
    };
}
