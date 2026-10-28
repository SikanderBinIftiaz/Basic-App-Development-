package com.example.commonscreens;

import android.util.Patterns;
import android.widget.EditText;

/** Small helper methods for reading and validating input fields. */
public class Validators {

    private Validators() { }   // utility class: no objects needed

    /** Trimmed text of an EditText (never null). Use for name/email. */
    public static String text(EditText editText) {
        return editText.getText() == null ? "" : editText.getText().toString().trim();
    }

    /** Raw text, NOT trimmed. Use for passwords (spaces may be intentional). */
    public static String raw(EditText editText) {
        return editText.getText() == null ? "" : editText.getText().toString();
    }

    /** True if the text looks like an email address. */
    public static boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    /** Password rule: at least 6 characters. */
    public static boolean isValidPassword(String password) {
        return password.length() >= 6;
    }
}
