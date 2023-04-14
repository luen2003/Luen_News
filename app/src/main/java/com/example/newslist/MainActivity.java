package com.example.newslist;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private WebView mWebview;

    ListView list;

    String[] maintitle = {
            "Github", "VnExpress",
            "Vietnamnet", "Bao moi",
            "Bao Tuoi tre",
    };

    String[] subtitle = {
            "My github page", "VnExpress Page",
            "Vietnamnet Page", "Bao moi Page",
            "Bao tuoi tre Page",
    };
    String[] urls = {
            "https://raw.githack.com/luen2003/CSSWebsite/master/index.htm",
            "https://vnexpress.net/",
            "https://vietnamnet.vn/",
            "https://baomoi.com/",
            "https://tuoitre.vn/",
    };

    Integer[] imgid = {
            R.drawable.news, R.drawable.news,
            R.drawable.news, R.drawable.news,
            R.drawable.news,
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && this.mWebview.canGoBack()) {
            this.mWebview.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyListAdapter adapter = new MyListAdapter(this, maintitle, subtitle, imgid, urls);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
        mWebview = new WebView(this);

        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.getSettings().setSupportZoom(true);
        mWebview.getSettings().setAllowContentAccess(true);
        mWebview.getSettings().setBuiltInZoomControls(true);
        mWebview.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        mWebview.getSettings().setDisplayZoomControls(false);
        final Activity activity = this;
        mWebview.setWebViewClient(new WebViewClient());


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                mWebview.loadUrl(urls[position]);
                setContentView(mWebview);
                Toast.makeText(activity, "Welcome to Luen News", Toast.LENGTH_LONG).show();
            }
        });
    }
}