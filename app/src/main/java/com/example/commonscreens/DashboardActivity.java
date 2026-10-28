package com.example.commonscreens;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/** Dashboard: shows module cards. Profile opens ProfileActivity; others show a Toast. */
public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Show "Welcome, <name>"
        TextView tvWelcome = findViewById(R.id.tvWelcome);
        tvWelcome.setText(getString(R.string.welcome_user, new UserStore(this).getName()));

        // Profile card -> explicit Intent to ProfileActivity
        findViewById(R.id.cardProfile).setOnClickListener(v ->
                startActivity(new Intent(DashboardActivity.this, ProfileActivity.class)));

        // The other cards are placeholders: they just show a message.
        // Two parallel arrays: card ids and their names.
        int[] cardIds = {R.id.cardMessages, R.id.cardNotifications,
                R.id.cardFavorites, R.id.cardSecurity, R.id.cardAbout};
        int[] labels = {R.string.module_messages, R.string.module_notifications,
                R.string.module_favorites, R.string.module_security, R.string.module_about};

        // Loop through the cards and set a click listener on each
        for (int i = 0; i < cardIds.length; i++) {
            final int label = labels[i];
            findViewById(cardIds[i]).setOnClickListener(v ->
                    Toast.makeText(this,
                            getString(R.string.module_opened, getString(label)),
                            Toast.LENGTH_SHORT).show());
        }
    }
}
