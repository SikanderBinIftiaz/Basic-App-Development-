package com.example.commonscreens;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Simulated local "backend".
 * Stores one user in SharedPreferences (a small key-value file on the phone).
 * Default demo account: demo@example.com / Demo@123 (username: demo)
 */
public class UserStore {

    // Keys used to save/read values
    private static final String PREFS = "auth_prefs";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_LOGGED_IN = "logged_in";

    // Hardcoded demo user (used until someone registers)
    private static final String DEFAULT_NAME = "Demo User";
    private static final String DEFAULT_EMAIL = "demo@example.com";
    private static final String DEFAULT_PASSWORD = "Demo@123";

    private final SharedPreferences prefs;

    public UserStore(Context context) {
        prefs = context.getApplicationContext().getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    public String getName() {
        return prefs.getString(KEY_NAME, DEFAULT_NAME);
    }

    public String getEmail() {
        return prefs.getString(KEY_EMAIL, DEFAULT_EMAIL);
    }

    private String getPassword() {
        return prefs.getString(KEY_PASSWORD, DEFAULT_PASSWORD);
    }

    // Username = the part of the email before the "@"
    private String getUsername() {
        String email = getEmail();
        int at = email.indexOf('@');
        return at > 0 ? email.substring(0, at) : email;
    }

    /** Returns true if email/username and password match; also marks the user as logged in. */
    public boolean login(String idOrEmail, String password) {
        boolean idOk = idOrEmail.equalsIgnoreCase(getEmail())
                || idOrEmail.equalsIgnoreCase(getUsername());
        if (idOk && password.equals(getPassword())) {
            prefs.edit().putBoolean(KEY_LOGGED_IN, true).apply();
            return true;
        }
        return false;
    }

    /** Saves a new user (replaces the demo user). */
    public void register(String name, String email, String password) {
        prefs.edit()
                .putString(KEY_NAME, name)
                .putString(KEY_EMAIL, email)
                .putString(KEY_PASSWORD, password)
                .putBoolean(KEY_LOGGED_IN, false)
                .apply();
    }

    /** Used by Forgot Password to check that the email is registered. */
    public boolean emailExists(String email) {
        return email.equalsIgnoreCase(getEmail());
    }

    /** Used by Reset Password to save the new password. */
    public void updatePassword(String newPassword) {
        prefs.edit().putString(KEY_PASSWORD, newPassword).apply();
    }

    public boolean isLoggedIn() {
        return prefs.getBoolean(KEY_LOGGED_IN, false);
    }

    public void logout() {
        prefs.edit().putBoolean(KEY_LOGGED_IN, false).apply();
    }
}
