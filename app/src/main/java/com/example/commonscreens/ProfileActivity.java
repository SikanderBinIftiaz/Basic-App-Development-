package com.example.commonscreens;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/** Profile: shows the user's info and lets them log out. */
public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        UserStore store = new UserStore(this);

        TextView tvName = findViewById(R.id.tvName);
        TextView tvEmail = findViewById(R.id.tvEmail);
        Button btnLogout = findViewById(R.id.btnLogout);

        // Display the saved user info
        tvName.setText(store.getName());
        tvEmail.setText(store.getEmail());

        // Logout: clear the login state, then go back to Login
        btnLogout.setOnClickListener(v -> {
            store.logout();

            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
            // Clear the whole back stack so Back can't return to Profile/Dashboard
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }
}
