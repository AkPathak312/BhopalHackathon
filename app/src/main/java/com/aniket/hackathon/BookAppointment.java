package com.aniket.hackathon;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Random;

public class BookAppointment extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    Button dtbutton,confirm;
    private DatabaseReference reference;
    TextView txtreason;

    TextView txtdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);
        txtdate=findViewById(R.id.txtdateshow);
        dtbutton=findViewById(R.id.datebtn);
        txtreason=findViewById(R.id.txtdescription);
        confirm=findViewById(R.id.btnconfirm);
        reference= FirebaseDatabase.getInstance().getReference("appointment");
        dtbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new DatePickerFragment();
                dialogFragment.show(getSupportFragmentManager(), "date picker");
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GlobalVariables.date=txtdate.getText().toString();
                AppointmentModel appointmentModel=new AppointmentModel(GlobalVariables.appointeddoctor,GlobalVariables.date,txtreason.getText().toString());

                Random random=new Random();
                int n=random.nextInt(50000);
                reference.child("appoint"+n).setValue(appointmentModel);

                Intent i=new Intent(BookAppointment.this,AppointmentConfirmation.class);
                startActivity(i);
                finish();
              //  Toast.makeText(BookAppointment.this, "Appointment Made", Toast.LENGTH_SHORT).show();
               // startActivity(new Intent(BookAppointment.this,DashBoard.class));
               // finish();


            }
        });

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c=Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        String selecteddate= DateFormat.getDateInstance().format(c.getTime());
        txtdate.setText(selecteddate);
    }
}
