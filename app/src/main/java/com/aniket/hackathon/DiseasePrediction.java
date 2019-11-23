package com.aniket.hackathon;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DiseasePrediction extends AppCompatActivity {

    private DatabaseReference reference;

    String[] symptoms={"back_pain","constipation","abdominal_pain","diarrhoea","mild_fever","yellow_urine",
            "yellowing_of_eyes","acute_liver_failure","fluid_overload","swelling_of_stomach",
            "swelled_lymph_nodes","malaise","blurred_and_distorted_vision","phlegm","throat_irritation",
            "redness_of_eyes","sinus_pressure","runny_nose","congestion","chest_pain","weakness_in_limbs",
            "fast_heart_rate","pain_during_bowel_movements","pain_in_anal_region","bloody_stool",
            "irritation_in_anus","neck_pain","dizziness","cramps","bruising","obesity","swollen_legs",
            "swollen_blood_vessels","puffy_face_and_eyes","enlarged_thyroid","brittle_nails",
            "swollen_extremeties","excessive_hunger","extra_marital_contacts","drying_and_tingling_lips",
            "slurred_speech","knee_pain","hip_joint_pain","muscle_weakness","stiff_neck","swelling_joints",
            "movement_stiffness","spinning_movements","loss_of_balance","unsteadiness",
            "weakness_of_one_body_side","loss_of_smell","bladder_discomfort","foul_smell_of urine",
            "continuous_feel_of_urine","passage_of_gases","internal_itching","toxic_look_(typhos)",
            "depression","irritability","muscle_pain","altered_sensorium","red_spots_over_body","belly_pain",
            "abnormal_menstruation","dischromic _patches","watering_from_eyes","increased_appetite","polyuria","family_history","mucoid_sputum",
            "rusty_sputum","lack_of_concentration","visual_disturbances","receiving_blood_transfusion",
            "receiving_unsterile_injections","coma","stomach_bleeding","distention_of_abdomen",
            "history_of_alcohol_consumption","fluid_overload","blood_in_sputum","prominent_veins_on_calf",
            "palpitations","painful_walking","pus_filled_pimples","blackheads","scurring","skin_peeling",
            "silver_like_dusting","small_dents_in_nails","inflammatory_nails","blister","red_sore_around_nose",
            "yellow_crust_ooze"};
    Spinner a,b,c,d,e;
    Button btnDetect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_prediction);
        a=findViewById(R.id.spinner1);
        b=findViewById(R.id.spinner2);
        c=findViewById(R.id.spinner3);
        d=findViewById(R.id.spinner4);
        e=findViewById(R.id.spinner5);
        btnDetect=findViewById(R.id.btndetect);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,symptoms);
        a.setAdapter(adapter);
        b.setAdapter(adapter);
        c.setAdapter(adapter);
        d.setAdapter(adapter);
        e.setAdapter(adapter);
        reference= FirebaseDatabase.getInstance().getReference("diseaseprediction");

        btnDetect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1=a.getSelectedItem().toString();
                String s2=b.getSelectedItem().toString();
                String s3=c.getSelectedItem().toString();
                String s4=d.getSelectedItem().toString();
                String s5=e.getSelectedItem().toString();
                //Toast.makeText(DiseasePrediction.this, s1, Toast.LENGTH_SHORT).show();

                SymptomModel symptomModel=new SymptomModel(s1,s2,s3,s4,s5);

                reference.child("symptom").setValue(symptomModel);
                startActivity(new Intent(DiseasePrediction.this,WaitingScreen.class));

            }
        });
    }


}
