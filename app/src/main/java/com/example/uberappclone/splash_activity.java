package com.example.uberappclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class splash_activity extends AppCompatActivity {
    private static boolean splashActivityShown = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // If the activity has been shown before, finish it and move to the next activity
        if (splashActivityShown) {
            navigateToNextActivity();
            return;
        }

        setContentView(R.layout.activity_splash);

        // Add any necessary initialization code or animations here
        // Example: You can use a Handler to navigate to the next screen after a delay
        new Handler().postDelayed(() -> {
            // Set the flag to indicate that the activity has been shown
            splashActivityShown = true;

            // Start your login activity
            navigateToNextActivity();
        }, 4000); // 2000 milliseconds (2 seconds) delay
    }

    private void navigateToNextActivity() {
        Intent intent = new Intent(splash_activity.this, activity_login.class);
        startActivity(intent);
        finish();
    }
}

