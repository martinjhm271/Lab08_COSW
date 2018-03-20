package com.example.martin.lab08;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;




public class MainActivity extends AppCompatActivity {
    EditText t= null;
    ImageView iv=null;
    private String imagePath;
    final int REQUEST_CAMERA = 1;
    final int SELECT_FILE = 2;
    final CharSequence[] items = {"Take Photo", "Choose From Gallery"};
    Uri imageSelected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

                Dialog d=createSingleChoiceAlertDialog(MainActivity.this,"Choose picture",items,optionSelectedListener,cancelListener);
                d.show();

            }
        });


        final Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                save();
            }
        });

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
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {
            case REQUEST_CAMERA:
                if(resultCode == RESULT_OK){
                    Bitmap bitmap = (Bitmap) imageReturnedIntent.getExtras().get("data");
                    iv.setImageBitmap(bitmap);
                    break;
                }

                break;
            case SELECT_FILE:
                if(resultCode == RESULT_OK){
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
            EditText editText = findViewById(R.id.editText);
            String message = editText.getText().toString();
            Bundle b =new Bundle();b.putSerializable("post",new Post(message,imageSelected));
            intent.putExtras(b);
            startActivity(intent);
        }

    }
    public boolean validation(){

        if(t.getText().toString().length()==0 && iv.getDrawable()==null){
            t.setError("Please enter either a message or select an image");
            return false;
        }
        if(t.getText().toString().length()<50){
            t.setError("The text field should have a length longer than 50 characters");
            return false;
        }
        return true;
    }


}
