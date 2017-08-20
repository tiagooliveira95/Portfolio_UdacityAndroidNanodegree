package com.smartguygoescrazy.udacity.android.portfolio;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tiago on 15/08/2017.
 */
class Item implements Parcelable {
    private String title, shortDescription, fullDescription;
    private int imageRes = -1;

    static final String KEY_TITLE = "title";

    Item(String title, String description, String fullDescription, int imageRes) {
        this.title = title;
        this.shortDescription = description;
        this.fullDescription = fullDescription;
        this.imageRes = imageRes;
    }

    private Item(Parcel in) {
        title = in.readString();
        shortDescription = in.readString();
        fullDescription = in.readString();
        imageRes = in.readInt();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    String getTitle() {
        return title;
    }


    String getShortDescription() {
        return shortDescription;
    }


    String getFullDescription() {
        return fullDescription;
    }

    int getImageRes() {
        return imageRes;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(shortDescription);
        parcel.writeString(fullDescription);
        parcel.writeInt(imageRes);
    }
}
