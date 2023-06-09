package com.sotdogn.bldogkepd.activties;


import static com.luciada.modids.MyAdZOne.Privacy_Policy;
import static com.luciada.modids.MyAdZOne.app_DeveloperOption_Check_Mode;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.sotdogn.bldogkepd.R;
import com.sotdogn.bldogkepd.utility.DevModeOptionCheck;

public class PravacyPolicyActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pravcy_policy);

        if(app_DeveloperOption_Check_Mode.equalsIgnoreCase("true")){
            DevModeOptionCheck.getInstance(this).DevMode_Check();
        }

        final CardView spinKitView = findViewById(R.id.spin_kit);
        spinKitView.setVisibility(View.VISIBLE);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                spinKitView.setVisibility(View.GONE);
            }
        }, 1000);

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAGS_CHANGED);
        window.setStatusBarColor(getResources().getColor(R.color.white));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.VISIBLE);//  set status text dark
        }

        WebView browser = (WebView) findViewById(R.id.webview);
        browser.setBackgroundColor(Color.TRANSPARENT);
        browser.getSettings().setJavaScriptEnabled(true);
        browser.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                view.loadUrl(
                        "javascript:document.body.style.setProperty(\"color\", \"black\");"
                );
            }
        });
        browser.loadUrl(Privacy_Policy);



    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(PravacyPolicyActivity.this, MainHomeActivity.class);
        startActivity(intent);
        finish();
    }
}