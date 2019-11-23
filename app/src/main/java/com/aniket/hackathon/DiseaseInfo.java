package com.aniket.hackathon;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DiseaseInfo extends AppCompatActivity {

    private Button gobacktoprediction;
    private TextView tv1,tv2,tv3;
    FirebaseDatabase database;
    DatabaseReference ref;
    String d1,d2,d3;
  //  private ListView listView;
    //String name[]={"Dr S. Singh","Dr. K.K Pandy"},post[]={" "," "},address[]={"Sector 39, Noida","New Delhi"},fee[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_info);
       // listView=findViewById(R.id.recommended);
        gobacktoprediction=findViewById(R.id.btnbacktoprediction);
        tv1=findViewById(R.id.txtRandomForest);
        tv2=findViewById(R.id.txtDecisionTree);
        tv3=findViewById(R.id.txtBayesNaive);
        gobacktoprediction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(DiseaseInfo.this,DiseasePrediction.class);
                startActivity(i);
                finish();
            }
        });

        database=FirebaseDatabase.getInstance();
        ref=database.getReference("diseaseprediction");
        final ProgressDialog progressDialog=new ProgressDialog(this);

        progressDialog.setTitle("Fetching ...");
        progressDialog.setMessage("Please wait while we get results...");
        progressDialog.show();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                int i=0;
                int j=(int)dataSnapshot.getChildrenCount();
                for(DataSnapshot ds: dataSnapshot.getChildren()){

                    d1=ds.child("firstprediction").getValue()+"";
                    d2=ds.child("secondprediction").getValue()+"";
                    d3=ds.child("thirdprediction").getValue()+"";
                    i=i+1;
                }

                tv1.setText(d1);
                tv2.setText(d2);
                tv3.setText(d3);
             //   Doctors.CustomAdapter customAdapter=new Doctors.CustomAdapter();
              //  listView.setAdapter(customAdapter);
                progressDialog.hide();
                progressDialog.cancel();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

}
