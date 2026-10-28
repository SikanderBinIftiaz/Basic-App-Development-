package com.example.commonscreens;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

/** Register screen: validates user input, creates a new account in UserStore, and returns to Login. */
public class RegisterActivity extends AppCompatActivity {

    private TextInputLayout tilName, tilEmail, tilPassword, tilConfirm;
    private TextInputEditText etName, etEmail, etPassword, etConfirm;
    private UserStore store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        store = new UserStore(this);

        tilName = findViewById(R.id.tilName);
        tilEmail = findViewById(R.id.tilEmail);
        tilPassword = findViewById(R.id.tilPassword);
        tilConfirm = findViewById(R.id.tilConfirm);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirm = findViewById(R.id.etConfirm);

        Button btnRegister = findViewById(R.id.btnRegister);
        TextView tvLogin = findViewById(R.id.tvLogin);

        btnRegister.setOnClickListener(v -> attemptRegister());

        tvLogin.setOnClickListener(v -> finish());
    }

    private void attemptRegister() {
        tilName.setError(null);
        tilEmail.setError(null);
        tilPassword.setError(null);
        tilConfirm.setError(null);

        String name = Validators.text(etName);
        String email = Validators.text(etEmail);
        String password = Validators.raw(etPassword);
        String confirm = Validators.raw(etConfirm);

        boolean valid = true;

        if (name.isEmpty()) {
            tilName.setError(getString(R.string.err_required));
            valid = false;
        }

        if (email.isEmpty()) {
            tilEmail.setError(getString(R.string.err_required));
            valid = false;
        } else if (!Validators.isValidEmail(email)) {
            tilEmail.setError(getString(R.string.err_email));
            valid = false;
        }

        if (password.isEmpty()) {
            tilPassword.setError(getString(R.string.err_required));
            valid = false;
        } else if (!Validators.isValidPassword(password)) {
            tilPassword.setError(getString(R.string.err_password_len));
            valid = false;
        }

        if (confirm.isEmpty()) {
            tilConfirm.setError(getString(R.string.err_required));
            valid = false;
        } else if (!confirm.equals(password)) {
            tilConfirm.setError(getString(R.string.err_password_match));
            valid = false;
        }

        if (!valid) return;

        store.register(name, email, password);
        Toast.makeText(this, R.string.register_success, Toast.LENGTH_LONG).show();

        finish();
    }
}
