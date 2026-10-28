package com.example.commonscreens;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

/** Shows the logo/name for 2 seconds, then opens Login (or Dashboard if already logged in). */
public class SplashActivity extends AppCompatActivity {

    private static final long SPLASH_DELAY_MS = 2000;   // 2 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);       // attach the XML layout

        // Run this code after the delay
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            // If the user is already logged in, skip Login
            Class<?> next = new UserStore(this).isLoggedIn()
                    ? DashboardActivity.class
                    : LoginActivity.class;

            // EXPLICIT INTENT: we name the exact Activity class to open
            startActivity(new Intent(SplashActivity.this, next));
            finish();   // remove Splash from the back stack so Back doesn't return to it
        }, SPLASH_DELAY_MS);
    }
}
