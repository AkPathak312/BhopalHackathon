package com.aniket.hackathon;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcode;
import com.google.firebase.ml.vision.barcode.FirebaseVisionBarcodeDetector;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;

import java.util.List;

import javax.xml.transform.Result;

public class DashBoard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==100){
            if(resultCode== RESULT_OK){
                Bitmap photo=(Bitmap)data.getExtras().get("data");
                FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(photo);
                FirebaseVisionBarcodeDetector detector = FirebaseVision.getInstance()
                        .getVisionBarcodeDetector();

                Task<List<FirebaseVisionBarcode>> result = detector.detectInImage(image)
                        .addOnSuccessListener(new OnSuccessListener<List<FirebaseVisionBarcode>>() {
                            @Override
                            public void onSuccess(List<FirebaseVisionBarcode> barcodes) {
                                // Task completed successfully
                                // ...
                                for (FirebaseVisionBarcode barcode: barcodes) {
                                    Rect bounds = barcode.getBoundingBox();
                                    Point[] corners = barcode.getCornerPoints();

                                    String rawValue = barcode.getRawValue();

                                    int valueType = barcode.getValueType();
                                    // See API reference for complete list of supported types
                                    GlobalVariables.shopname=rawValue;
                                    Intent i=new Intent(DashBoard.this,UploadPrescription.class);
                                    startActivity(i);


                                    //Toast.makeText(DashBoard.this, rawValue, Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Task failed with an exception
                                // ...
                                Toast.makeText(DashBoard.this, "Failed", Toast.LENGTH_SHORT).show();
                            }
                        });

            }

        }
    }

    public void gotodoctors(View view) {
        startActivity(new Intent(DashBoard.this,Doctors.class));
    }

    public void gotogovt(View view) {

        startActivity(new Intent(DashBoard.this,GovtInitiatives.class));
    }

    public void goback(View view) {
        startActivity(new Intent(DashBoard.this,MainActivity.class));
        finish();
    }

    public void gotocomplains(View view) {
        startActivity(new Intent(DashBoard.this,Complains.class));
    }

    public void gotohospitals(View view) {

        startActivity(new Intent(DashBoard.this,Hospitals.class));
    }

    public void gotosurvey(View view) {


    }

    public void gototestimonials(View view) {

      if(ContextCompat.checkSelfPermission(DashBoard.this,
              Manifest.permission.CAMERA)==PackageManager.PERMISSION_GRANTED){


          Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
          startActivityForResult(i,100);
          Toast.makeText(DashBoard.this, "Permission already granted", Toast.LENGTH_SHORT).show();
          
      }else{
          requestCameraPermission();

      }



    }

    private void requestCameraPermission() {

        if(ActivityCompat.shouldShowRequestPermissionRationale(DashBoard.this,
                Manifest.permission.CAMERA)){
            new AlertDialog.Builder(DashBoard.this)
                    .setTitle("Needed")
                    .setMessage("Needs Permission")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            ActivityCompat.requestPermissions(DashBoard.this,new String[]{Manifest.permission.CAMERA},
                                    1);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create().show();

        }
        else{
            ActivityCompat.requestPermissions(DashBoard.this,new String[]{Manifest.permission.CAMERA},
                    1);

        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==1){
            if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(DashBoard.this, "Granted", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(DashBoard.this, "Not Granted", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
