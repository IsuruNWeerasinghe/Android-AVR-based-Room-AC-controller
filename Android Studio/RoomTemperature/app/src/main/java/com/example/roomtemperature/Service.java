package com.example.roomtemperature;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toolbar;

import static android.os.Build.VERSION_CODES.M;

public class Service extends AppCompatActivity {

    private WebView serviceweb,serviceweb1;
    private Toolbar serviceToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        serviceweb = (WebView)findViewById(R.id.serviceweb);
        serviceweb1 = (WebView)findViewById(R.id.serviceweb1);
        serviceToolbar = (Toolbar)findViewById(R.id.servicetoolbar);

        serviceToolbar.setTitle("Service");
        serviceToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        serviceToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        serviceweb.setWebViewClient(new WebViewClient());
        String serviceurl = "https://www.abanservice.lk/";
        serviceweb.getSettings().setJavaScriptEnabled(true);
        serviceweb.loadUrl(serviceurl); // load a web page in a web view

        serviceweb1.setWebViewClient(new WebViewClient());
        String serviceurl1 = "http://www.coolairservicessrilanka.com/";
        serviceweb1.getSettings().setJavaScriptEnabled(true);
        serviceweb1.loadUrl(serviceurl1); // load a web page in a web view
    }
}
