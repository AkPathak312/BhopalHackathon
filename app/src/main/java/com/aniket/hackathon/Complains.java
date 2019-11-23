package com.aniket.hackathon;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Complains extends AppCompatActivity {
    private DatabaseReference reference;
    ListView listView;
    Button submit;
    EditText editText;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complains);
        List<String> list = new ArrayList<String>();
        list.add("Interface");
        list.add("Doctors");
        list.add("Hospitals");
        list.add("Lab Tests");
        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<>(Complains.this,R.layout.support_simple_spinner_dropdown_item,list);
        spinner=findViewById(R.id.spinner);
        submit=findViewById(R.id.btnSubmit);
        editText=findViewById(R.id.edtcomplain);
        spinner.setAdapter(arrayAdapter);
        spinner.setSelection(0);
        reference= FirebaseDatabase.getInstance().getReference("complains");
        Random random=new Random();
        final int n=random.nextInt(10000000);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String complain=editText.getText().toString();
                final String category=spinner.getSelectedItem().toString();
                ComplainModel complainModel=new ComplainModel(category,complain);
                reference.child(category+n).setValue(complainModel);

                AlertDialog.Builder builder = new AlertDialog.Builder(Complains.this);
                builder.setMessage("Your complain has been recorded.We will contact you soon :)")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                startActivity(new Intent(Complains.this,MainDashBoard.class));
                                finish();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
                editText.setText("");

            }
        });

    }
}
