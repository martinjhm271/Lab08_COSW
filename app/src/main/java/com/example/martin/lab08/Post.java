package com.example.martin.lab08;

/**
 * Created by martin on 19/03/18.
 */


import android.net.Uri;

import java.io.Serializable;

public class Post implements Serializable {



    private String message;
    private Uri imageUri;

    public Post(String message,Uri imageUri){
        this.message=message;
        this.imageUri=imageUri;
    }

    public Post(){}

    public String getMessage(){return this.message;}
    public Uri getImageUri(){return this.imageUri;}
    public void setMessage(String m){this.message=m;}
    public void setImageUri(Uri i){this.imageUri=i;}
}
