package com.myECapplication.sajiiidali.ecsaver;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

public class WELCOM_SCREEN extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom__screen);

        Handler myhandler = new Handler();
        myhandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent it = new Intent(WELCOM_SCREEN.this, MainActivity.class);
                startActivity(it);
                finish();
            }
        },500);
    }
}
