package com.example.webtalk.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.webtalk.Activity.MainActivity;
import com.example.webtalk.R;

public class Intro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        Handler intro_handlr = new Handler();
        intro_handlr.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent Login_intent = new Intent(getApplicationContext() , Login.class);
                startActivity(Login_intent);
                finish();
            }
        },3000);
    }

    protected void onPause() {
        super.onPause();
        finish();
    }
}
