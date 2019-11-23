package com.aniket.hackathon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class AppointmentConfirmation extends AppCompatActivity {

    Button btnBack;
    TextView txtDate,txtTime,txtSerial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_confirmation);
        txtDate=findViewById(R.id.txtDate);
        txtTime=findViewById(R.id.txtTime);
        txtSerial=findViewById(R.id.txtSerial);
        Random random=new Random();
        int serail=random.nextInt(30);
        txtSerial.setText("Serial Number : "+serail);
        if(serail<6)
            txtTime.setText("Time : 11:00 AM");
        else if(serail>=6&&serail<12)
            txtTime.setText("Time : 12:00 AM");
        else if(serail>=12&&serail<18)
            txtTime.setText("Time : 01:00 AM");
        else if(serail>=18&&serail<24)
            txtTime.setText("Time : 02:00 AM");
        else
            txtTime.setText("Time : 3:00 PM");
        txtDate.setText("Date : "+GlobalVariables.date);
        btnBack=findViewById(R.id.btnBacktoDashboard);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(AppointmentConfirmation.this,MainDashBoard.class);
                startActivity(i);
                finish();
            }
        });


    }

}
