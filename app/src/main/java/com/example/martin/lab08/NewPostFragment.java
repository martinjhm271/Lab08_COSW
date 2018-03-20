package com.example.martin.lab08;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;




public class NewPostFragment extends Fragment {


    /**


    public static NewPostFragment newInstance(String param1, String param2) {
        NewPostFragment fragment = new NewPostFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.t= findViewById(R.id.editText);
        this.iv=findViewById(R.id.imageView);



        final Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                DialogInterface.OnClickListener optionSelectedListener =new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (items[which].equals("Take Photo")) {
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, REQUEST_CAMERA);
                        } else if (items[which].equals("Choose From Gallery")) {
                            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(intent,SELECT_FILE);
                        }
                    }
                };

                DialogInterface.OnClickListener cancelListener =new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                };

                Dialog d=createSingleChoiceAlertDialog(MainActivity.this,t.getText().toString(),items,optionSelectedListener,cancelListener);
                d.show();

            }
        });


        final Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                save();
            }
        });


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_post, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }



    @NonNull
    public static Dialog createSingleChoiceAlertDialog(@NonNull Context context, @Nullable String title,
                                                       @Nullable CharSequence[] items,
                                                       @NonNull DialogInterface.OnClickListener optionSelectedListener,
                                                       @Nullable DialogInterface.OnClickListener cancelListener )
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setItems( items, optionSelectedListener );
        if ( cancelListener != null ) {
            builder.setNegativeButton("Cancel", cancelListener );
        }
        builder.setTitle( title );
        return builder.create();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {
            case REQUEST_CAMERA:
                if(resultCode == -1){
                    Uri selectedImage = imageReturnedIntent.getData();
                    iv.setImageURI(selectedImage);
                    imageSelected=selectedImage;
                }

                break;
            case SELECT_FILE:
                if(resultCode == -1){
                    Uri selectedImage = imageReturnedIntent.getData();
                    iv.setImageURI(selectedImage);
                    imageSelected=selectedImage;
                }
                break;
        }
    }

    public void save(){
        if(validation()){
            Intent intent = new Intent(this, PostActivity.class);
            EditText editText = (EditText) findViewById(R.id.editText);
            String message = editText.getText().toString();
            Bundle b =new Bundle();b.putSerializable("post",new Post(message,imageSelected));
            intent.putExtra("post",b);
            startActivity(intent);
        }

    }
    public boolean validation(){

        if(t.getText().toString().length()==0 && imageSelected==null){
            t.setError("Please enter either a message or select an image");
            return false;
        }
        if(t.getText().toString().length()<50){
            t.setError("The text field should have a length longer than 50 characters");
            return false;
        }
        return true;
    }


*/

}
