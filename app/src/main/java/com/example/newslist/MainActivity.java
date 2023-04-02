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
        mWebview.setWebViewClient(new WebViewClient() {
            @SuppressWarnings("deprecation")
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
            }

            @TargetApi(android.os.Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
                // Redirect to deprecated method, so you can use it in all SDK versions
                onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
            }

        });


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                //Toast.makeText(getApplicationContext(),"Place Your First Option Code",Toast.LENGTH_SHORT).show();

                mWebview.loadUrl(urls[position]);
                setContentView(mWebview);
            }
        });
    }
}