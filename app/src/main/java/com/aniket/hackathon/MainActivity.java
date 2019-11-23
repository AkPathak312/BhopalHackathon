package com.aniket.hackathon;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;
    EditText email,password;
    TextView txtRegister;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        password=findViewById(R.id.editText2);
        email=findViewById(R.id.editText);
        btnLogin=findViewById(R.id.btnLogin);
        txtRegister=findViewById(R.id.txtRegister);



        mAuth=FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailid =email.getText().toString();
                String passw=password.getText().toString();
                GlobalVariables.mailid=emailid;
                //  SharedPreferences sp=getSharedPreferences("TAG", Context.MODE_PRIVATE);
                // sp.edit().putString("userid",emailid);
                // sp.edit().putString("pass",passw);

                //Toast.makeText(LoginScreen.this,""+ emailid,Toast.LENGTH_LONG).show();
                signin(emailid,passw);
            }
        });

    }
    private void signin(String email,String password){

        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Signing In...");
        progressDialog.setMessage("Please wait while we sign you in....");
        progressDialog.show();
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    progressDialog.hide();
                    progressDialog.cancel();
                    Toast.makeText(MainActivity.this,"Unable to Login",Toast.LENGTH_LONG).show();
                }
                else
                {
                    progressDialog.hide();
                    progressDialog.cancel();
                    Intent i=new Intent(MainActivity.this,MainDashBoard.class);
                    startActivity(i);
                    finish();
                }
            }
        });


    }
}
