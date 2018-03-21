package com.example.martin.lab08;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import java.nio.ByteBuffer;


public class PostActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        Intent intent = getIntent();
        Post p = (Post) intent.getExtras().getParcelable("post");

        TextView textView = findViewById(R.id.textView4);
        String m =p.getMessage();
        textView.setText(m);
        ImageView imageView = findViewById(R.id.imageView4);
        Bitmap b = p.getImageUri();
        imageView.setImageBitmap(b);
    }




}

