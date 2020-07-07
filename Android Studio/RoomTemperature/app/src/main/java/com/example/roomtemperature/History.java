package com.example.roomtemperature;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toolbar;

public class History extends AppCompatActivity {

    private WebView tempview,humiview,doorview;
    private Toolbar historyToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        historyToolbar = (Toolbar)findViewById(R.id.historytoolbar);
        historyToolbar.setTitle("History");
        historyToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        historyToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        tempview = (WebView)findViewById(R.id.tempWebView);

        tempview.setWebViewClient(new MyWebViewClient());
        String tempurl = "https://thingspeak.com/channels/807302";
        tempview.getSettings().setJavaScriptEnabled(true);
        tempview.loadUrl(tempurl); // load a web page in a web view
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
