package com.lmf.appmovies.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.lmf.appmovies.ui.main.MainActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Intent i = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(i);
        finish();

//        Handler handle = new Handler();
//        handle.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//                Intent i = new Intent(SplashActivity.this, MainActivity.class);
//                startActivity(i);
//                finish();
//
//            }
//        }, 2000);

    }
}