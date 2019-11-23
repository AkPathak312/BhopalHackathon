package com.aniket.hackathon;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class InitView extends AppCompatActivity {

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webView=findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(GlobalVariables.loadurl);
        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        ;
    }

    public void interested(View view) {

        AlertDialog.Builder builder
                = new AlertDialog
                .Builder(InitView.this);
        builder.setMessage("Thanks for showing interest in the scheme.One of our representative will contact you soon regarding this.");
        builder.setTitle("Response");
        builder
                .setPositiveButton(
                        "Okay",
                        new DialogInterface
                                .OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which)
                            {

                                // When the user click yes button
                                // then app will close
                                finish();
                            }
                        });

        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
}
