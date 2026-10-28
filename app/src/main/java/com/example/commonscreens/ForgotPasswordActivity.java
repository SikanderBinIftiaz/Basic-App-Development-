package com.example.commonscreens;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

/** Forgot Password: validates the email and simulates sending a recovery link. */
public class ForgotPasswordActivity extends AppCompatActivity {

    private TextInputLayout tilEmail;
    private TextInputEditText etEmail;
    private UserStore store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        store = new UserStore(this);

        tilEmail = findViewById(R.id.tilEmail);
        etEmail = findViewById(R.id.etEmail);
        Button btnSend = findViewById(R.id.btnSend);
        TextView tvBack = findViewById(R.id.tvBack);

        btnSend.setOnClickListener(v -> attemptRecovery());

        // finish() closes this screen and returns to the previous one (Login)
        tvBack.setOnClickListener(v -> finish());
    }

    private void attemptRecovery() {
        tilEmail.setError(null);
        String email = Validators.text(etEmail);

        // 1) Email is required
        if (email.isEmpty()) {
            tilEmail.setError(getString(R.string.err_required));
            return;
        }
        // 2) Email must be in a valid format
        if (!Validators.isValidEmail(email)) {
            tilEmail.setError(getString(R.string.err_email));
            return;
        }
        // 3) Email must belong to the (simulated) registered user
        if (!store.emailExists(email)) {
            tilEmail.setError(getString(R.string.err_no_account));
            return;
        }

        // Simulated "email sent" message
        Toast.makeText(this, getString(R.string.recovery_sent, email), Toast.LENGTH_LONG).show();

        // Explicit Intent: go to Reset Password and pass the email as an extra
        Intent intent = new Intent(ForgotPasswordActivity.this, ResetPasswordActivity.class);
        intent.putExtra("email", email);
        startActivity(intent);
    }
}
