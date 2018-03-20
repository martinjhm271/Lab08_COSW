package com.example.martin.lab08;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;



public class PostActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        Intent intent = getIntent();
        Post p = (Post) intent.getSerializableExtra("post");

        TextView textView = findViewById(R.id.textView2);
        textView.setText(p.getMessage());
        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageURI(p.getImageUri());
    }




}

