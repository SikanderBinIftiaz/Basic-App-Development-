package com.example.commonscreens;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

/** Reset Password: new password + confirm password with validation. */
public class ResetPasswordActivity extends AppCompatActivity {

    private TextInputLayout tilPassword, tilConfirm;
    private TextInputEditText etPassword, etConfirm;
    private UserStore store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        store = new UserStore(this);

        tilPassword = findViewById(R.id.tilPassword);
        tilConfirm = findViewById(R.id.tilConfirm);
        etPassword = findViewById(R.id.etPassword);
        etConfirm = findViewById(R.id.etConfirm);
        Button btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> attemptReset());
    }

    private void attemptReset() {
        tilPassword.setError(null);
        tilConfirm.setError(null);

        String password = Validators.raw(etPassword);
        String confirm = Validators.raw(etConfirm);
        boolean valid = true;

        // New password: required + at least 6 characters
        if (password.isEmpty()) {
            tilPassword.setError(getString(R.string.err_required));
            valid = false;
        } else if (!Validators.isValidPassword(password)) {
            tilPassword.setError(getString(R.string.err_password_len));
            valid = false;
        }

        // Confirm: required + must match
        if (confirm.isEmpty()) {
            tilConfirm.setError(getString(R.string.err_required));
            valid = false;
        } else if (!confirm.equals(password)) {
            tilConfirm.setError(getString(R.string.err_password_match));
            valid = false;
        }

        if (!valid) return;

        // Save the new password (simulated) and go back to Login
        store.updatePassword(password);
        Toast.makeText(this, R.string.reset_success, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(ResetPasswordActivity.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
