package com.cashdash.finalproject.Activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.cashdash.finalproject.R;

public class SplashActivity extends AppCompatActivity {

    TextView appTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        appTitle = findViewById(R.id.appTitle);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "font/Roboto-Light.ttf");
        appTitle.setTypeface(typeface);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,HomeActivity.class));
                finish();
            }
        },3000);

    }
}
