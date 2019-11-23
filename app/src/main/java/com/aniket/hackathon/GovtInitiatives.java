package com.aniket.hackathon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GovtInitiatives extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_govt_initiatives);
    }

    public void gotowebview(View view) {

        GlobalVariables.loadurl="https://www.india.gov.in/spotlight/rashtriya-swasthya-bima-yojana";
        startActivity(new Intent(GovtInitiatives.this,InitView.class));

    }
    public void gotowebview1(View view) {

        GlobalVariables.loadurl="https://www.india.gov.in/spotlight/employees-state-insurance-scheme#tab=tab-5";
        startActivity(new Intent(GovtInitiatives.this,InitView.class));

    }
    public void gotowebview2(View view) {

        GlobalVariables.loadurl="http://vikaspedia.in/social-welfare/unorganised-sector-1/schemes-unorganised-sector/aam-admi-bima-yojana";
        startActivity(new Intent(GovtInitiatives.this,InitView.class));

    } public void gotowebview3(View view) {

        GlobalVariables.loadurl="http://vikaspedia.in/health/nrhm/national-health-programmes-1/pradhan-mantri-swasthya-suraksha-yojana-pmssy";
        startActivity(new Intent(GovtInitiatives.this,InitView.class));

    }

}
