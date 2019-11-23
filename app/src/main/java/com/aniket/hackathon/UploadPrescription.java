package com.aniket.hackathon;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class UploadPrescription extends AppCompatActivity {


    TextView shopinfo;
    Button submit,upload;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_prescription);
        shopinfo=findViewById(R.id.txtShopNumber);
        upload=findViewById(R.id.btnUpload);
        submit=findViewById(R.id.btnconfirmUpload);

        image=findViewById(R.id.imgprescrip);
        shopinfo.setText(GlobalVariables.shopname);

        //Code for u;loading image to Imagview.

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i,100);

            }
        });
        //code for submitting image to shop
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(UploadPrescription.this,UploadConfirmation.class);
                startActivity(i);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==100){
            if(resultCode==RESULT_OK){
                Bitmap photo=(Bitmap)data.getExtras().get("data");
                image.setImageBitmap(photo);

            }

        }
    }
}
