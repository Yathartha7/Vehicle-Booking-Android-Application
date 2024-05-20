package com.example.uberappclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class payment_splash extends AppCompatActivity {
    private static final long SPLASH_DISPLAY_DURATION = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_payment_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Create an Intent to start the MainActivity
                Intent mainIntent = new Intent(payment_splash.this, MainActivity.class);
                startActivity(mainIntent);

                // Finish the current activity to prevent the user from coming back to the splash
                finishAffinity();
            }
        }, SPLASH_DISPLAY_DURATION);


    }
}