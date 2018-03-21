package com.example.martin.lab08;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;




public class NewPostFragment extends Fragment {
    EditText t= null;
    ImageView iv=null;
    private String imagePath;
    final int REQUEST_CAMERA = 1;
    final int SELECT_FILE = 2;
    final CharSequence[] items = {"Take Photo", "Choose From Gallery"};
    Bitmap imageSelected;
    Context applicationContext;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public NewPostFragment() {
    }


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
        applicationContext = MainActivity.getContextOfApplication();
        this.t= getView().findViewById(R.id.editText3);
        this.iv=getView().findViewById(R.id.imageView3);

        final Button button3 = getView().findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                DialogInterface.OnClickListener optionSelectedListener =new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (items[which].equals("Take Photo")) {
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(intent, REQUEST_CAMERA);
                        } else if (items[which].equals("Choose From Gallery")) {
                            Intent intent = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
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

                Dialog d=createSingleChoiceAlertDialog(applicationContext,"Choose picture",items,optionSelectedListener,cancelListener);
                d.show();

            }
        });


        final Button button4 = getView().findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_post, container, false);
    }


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
                    Bitmap bitmap = (Bitmap) imageReturnedIntent.getExtras().get("data");
                    iv.setImageBitmap(bitmap);

                    final float densityMultiplier = this.getResources().getDisplayMetrics().density;
                    int h= (int) (100*densityMultiplier);
                    int w= (int) (h * bitmap.getWidth()/((double) bitmap.getHeight()));
                    bitmap=Bitmap.createScaledBitmap(bitmap, w, h, true);

                    imageSelected=bitmap;
                    break;
                }

                break;
            case SELECT_FILE:
                if(resultCode == -1){
                    try{
                        Uri imageUri = imageReturnedIntent.getData();
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(applicationContext.getContentResolver(), imageUri);
                        iv.setImageBitmap(bitmap);

                        final float densityMultiplier = this.getResources().getDisplayMetrics().density;
                        int h= (int) (100*densityMultiplier);
                        int w= (int) (h * bitmap.getWidth()/((double) bitmap.getHeight()));
                        bitmap=Bitmap.createScaledBitmap(bitmap, w, h, true);

                        imageSelected=bitmap;
                        break;
                    }catch(Exception e){}

                }
                break;
        }
    }

    public void save(){
        if(validation()){
            Intent intent = new Intent(NewPostFragment.this.getActivity(), NewPostFragment.class);
            EditText editText = getView().findViewById(R.id.editText3);
            String message = editText.getText().toString();
            Bundle b =new Bundle();b.putParcelable("post",new Post(message,imageSelected));
            intent.putExtras(b);
            startActivity(intent);
        }

    }
    public boolean validation(){

        if(t.getText().toString().length()==0 && iv.getDrawable()==null){
            t.setError("Please enter either a message or select an image");
            return false;
        }
        if(t.getText().toString().length()<1){
            t.setError("The text field should have a length longer than 50 characters");
            return false;
        }
        return true;
    }

}
