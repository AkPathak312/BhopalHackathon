package com.aniket.hackathon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MedicAroundMe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medic_around_me);
    }

    public void hospitalaround(View view) {
        Intent i=new Intent(MedicAroundMe.this,WebViewMap.class);
        startActivity(i);

    }

    public void doctorsaround(View view) {
        Intent i=new Intent(MedicAroundMe.this,Doctors.class);
        startActivity(i);


    }
}
