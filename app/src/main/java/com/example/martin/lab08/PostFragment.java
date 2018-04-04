package com.example.martin.lab08;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class PostFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private Post p;
    private View v;


    public PostFragment() {
    }

    public static PostFragment newInstance(String param1, String param2) {
        PostFragment fragment = new PostFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    public Post getP() {
        return p;
    }

    public void setP(Post p) {
        this.p = p;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.fragment_post, container, false);
        TextView textView = v.findViewById(R.id.textView4);
        String m =this.p.getMessage();
        textView.setText(m);
        ImageView imageView = v.findViewById(R.id.imageView4);
        Bitmap b = this.p.getImageUri();
        imageView.setImageBitmap(b);
        return this.v;
    }



}
