package com.aniket.hackathon;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.media.Rating;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Hospitals extends AppCompatActivity {

    String hname[],haddress[];
    FirebaseDatabase database;
    DatabaseReference ref;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospitals);
        listView=findViewById(R.id.hospitallistview);
        database=FirebaseDatabase.getInstance();
        ref=database.getReference("hospitalaroundme");
        final ProgressDialog progressDialog=new ProgressDialog(this);

        progressDialog.setTitle("Fetching Details...");
        progressDialog.setMessage("Please wait while we get hospitals details.");
        progressDialog.show();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i=0;
                int j=(int)dataSnapshot.getChildrenCount();
                hname=new String[j];
                haddress=new String[j];

                for(DataSnapshot ds: dataSnapshot.getChildren()){

                    hname[i]=ds.child("hname").getValue()+"";
                    haddress[i]=ds.child("adress").getValue()+"";
                  //  address[i]=ds.child("adress").getValue()+"";
                    i=i+1;
                }
                CustomAdapter customAdapter=new CustomAdapter();
                listView.setAdapter(customAdapter);
                progressDialog.hide();
                progressDialog.cancel();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return hname.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView=getLayoutInflater().inflate(R.layout.hospitalrow,null);
            TextView txtName=convertView.findViewById(R.id.hname);
            TextView txtaddress=convertView.findViewById(R.id.haddress);
            txtName.setText(hname[position]);
            txtaddress.setText(haddress[position]);
            RatingBar ratingBar=convertView.findViewById(R.id.ratingbar);

            ratingBar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(Hospitals.this, "fdfgfd", Toast.LENGTH_SHORT).show();
                }
            });
            return convertView;
        }
    }
}
