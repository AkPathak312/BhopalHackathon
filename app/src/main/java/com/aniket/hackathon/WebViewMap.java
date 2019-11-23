package com.aniket.hackathon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class WebViewMap extends AppCompatActivity {
    WebView webView;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_map);
        webView=findViewById(R.id.webview1);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.google.com/maps/search/hospitals/@28.5987873,77.3209549,15z/data=!3m1!4b1");
        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        btn=findViewById(R.id.btnlist);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WebViewMap.this,Hospitals.class));
            }
        });
    }
}
