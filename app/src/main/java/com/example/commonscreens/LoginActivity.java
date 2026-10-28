package com.example.commonscreens;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

/** Login screen: validates input, checks the simulated user, and links to Register / Forgot Password. */
public class LoginActivity extends AppCompatActivity {

    // TextInputLayout = the box (shows errors), TextInputEditText = where the user types
    private TextInputLayout tilId, tilPassword;
    private TextInputEditText etId, etPassword;
    private UserStore store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        store = new UserStore(this);

        // Connect Java variables to the views in the XML using their ids
        tilId = findViewById(R.id.tilId);
        tilPassword = findViewById(R.id.tilPassword);
        etId = findViewById(R.id.etId);
        etPassword = findViewById(R.id.etPassword);
        Button btnLogin = findViewById(R.id.btnLogin);
        TextView tvForgot = findViewById(R.id.tvForgot);
        TextView tvRegister = findViewById(R.id.tvRegister);

        // Button click: try to log in
        btnLogin.setOnClickListener(v -> attemptLogin());

        // Explicit Intent: Login -> ForgotPasswordActivity
        tvForgot.setOnClickListener(v ->
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class)));

        // Explicit Intent: Login -> RegisterActivity
        tvRegister.setOnClickListener(v ->
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class)));
    }

    /** Validates the fields, then checks the credentials. */
    private void attemptLogin() {
        // Clear old errors
        tilId.setError(null);
        tilPassword.setError(null);

        String id = Validators.text(etId);
        String password = Validators.raw(etPassword);
        boolean valid = true;

        // Validation: both fields are required
        if (id.isEmpty()) {
            tilId.setError(getString(R.string.err_required));
            valid = false;
        }
        if (password.isEmpty()) {
            tilPassword.setError(getString(R.string.err_required));
            valid = false;
        }
        if (!valid) return;   // stop here if something is wrong

        if (store.login(id, password)) {
            // Success: open Dashboard and clear the back stack so Back won't return to Login
            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else {
            // Wrong credentials
            Toast.makeText(this, R.string.err_invalid_credentials, Toast.LENGTH_LONG).show();
        }
    }
}
