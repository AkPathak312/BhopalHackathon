package com.aniket.hackathon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class UploadConfirmation extends AppCompatActivity {
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_confirmation);
        txt=findViewById(R.id.txtShop);
        txt.setText(txt.getText()+GlobalVariables.shopname+"\nYou will be notified when your medicines will be ready. ");
    }

    public void backtodashboard(View view) {

        Intent i=new Intent(UploadConfirmation.this,MainDashBoard.class);
        startActivity(i);
    }
}
