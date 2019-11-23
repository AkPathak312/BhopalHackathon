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

public class Doctors extends AppCompatActivity {
    private Button medicinecenter;
    private ListView listView;
    String name[],post[],address[],fee[];

    FirebaseDatabase database;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors);
        listView=findViewById(R.id.docrowlistview);

        database=FirebaseDatabase.getInstance();
        ref=database.getReference("doctoraroundme");
        final ProgressDialog progressDialog=new ProgressDialog(this);

        progressDialog.setTitle("Fetching Details...");
        progressDialog.setMessage("Please wait while we get official details.");
        progressDialog.show();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                int i=0;
                int j=(int)dataSnapshot.getChildrenCount();
                name=new String[j];
                address=new String[j];
                post=new String[j];

                for(DataSnapshot ds: dataSnapshot.getChildren()){

                    name[i]=ds.child("dname").getValue()+"";
                    post[i]=ds.child("work").getValue()+"";
                    address[i]=ds.child("adress").getValue()+"";
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

    //Custom Adapter to Show Doctor List
    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return name.length;
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
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView=getLayoutInflater().inflate(R.layout.doctorsrow,null);
            final TextView txtName=convertView.findViewById(R.id.txtName);
            TextView txtPost=convertView.findViewById(R.id.txtPost);
            TextView txtAddress =convertView.findViewById(R.id.txtAddress);
            Button btn=convertView.findViewById(R.id.btnFee);
            Button apponint=convertView.findViewById(R.id.btnAppointment);
            apponint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    GlobalVariables.appointeddoctor=name[position];
                    startActivity(new Intent(Doctors.this,BookAppointment.class));
                }
            });
            txtName.setText(name[position]);
            txtPost.setText(post[position]);
            txtAddress.setText(address[position]);

            return convertView;
        }
    }
}
