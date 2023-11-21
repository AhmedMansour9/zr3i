package com.schemecode.zr3i.ui.activities.montada_web_view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.schemecode.zr3i.R;

public class MontadaActivity extends AppCompatActivity {

    private WebView webViewMontada;
    private String url;
    private RelativeLayout rlBack ;
    private ProgressBar progressBarWebView ;
    private class HelloWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_montada);
        initUi();
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        Log.e("url", url);
        webViewMontada.getSettings().setJavaScriptEnabled(true);
        webViewMontada.getSettings().setLoadWithOverviewMode(true);
        webViewMontada.getSettings().setUseWideViewPort(true);
        webViewMontada.getSettings().setBuiltInZoomControls(true);
        webViewMontada.getSettings().setPluginState(WebSettings.PluginState.ON);
        webViewMontada.setWebViewClient(new HelloWebViewClient());
        webViewMontada.loadUrl(url);
        WebSettings settings = webViewMontada.getSettings();
        settings.setDomStorageEnabled(true);
        webViewMontada.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBarWebView.setVisibility(View.VISIBLE);
                webViewMontada.setVisibility(View.GONE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBarWebView.setVisibility(View.GONE);
                webViewMontada.setVisibility(View.VISIBLE);
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request,
                                        WebResourceError error) {
                super.onReceivedError(view, request, error);
            }
        });
    }

    private void initUi() {
        webViewMontada = findViewById(R.id.montada_web_view);
        progressBarWebView = findViewById(R.id.progress_bar_web_view);
        rlBack = findViewById(R.id.sub_back_image_relative);
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}