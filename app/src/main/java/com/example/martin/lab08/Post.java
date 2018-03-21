package com.example.martin.lab08;

/**
 * Created by martin on 19/03/18.
 */


import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Post implements Parcelable {



    private String message;
    private Bitmap imageUri;

    public Post(String message,Bitmap imageUri){
        this.message=message;
        this.imageUri=imageUri;
    }

    public Post(){}

    public String getMessage(){return this.message;}
    public Bitmap getImageUri(){return this.imageUri;}
    public void setMessage(String m){this.message=m;}
    public void setImageUri(Bitmap i){this.imageUri=i;}

    protected Post(Parcel in) {
        message = in.readString();
        imageUri = in.readParcelable(Bitmap.class.getClassLoader());
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(message);
        dest.writeParcelable(imageUri, flags);
    }


}
