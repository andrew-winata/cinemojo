package com.cinemojo.core.repository;

import android.content.Context;
import android.content.SharedPreferences;

import com.cinemojo.core.model.UserCredentials;

public class LoginSessionRepository {

    private SharedPreferences sharedPreferences;

    private static LoginSessionRepository mInstance;

    public static LoginSessionRepository getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new LoginSessionRepository(context);
        }
        return mInstance;
    }

    private LoginSessionRepository(Context context) {
        this.sharedPreferences = context.getSharedPreferences("LoginSession", Context.MODE_PRIVATE);
    }

    public void saveUserCredentials(UserCredentials userCredentials) {
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        editor.putString("username", userCredentials.getUsername());
        editor.putString("password", userCredentials.getPassword());
        editor.putBoolean("isAuthenticated", true);
        editor.commit();
    }

    public boolean isAuthenticated() {
        return this.sharedPreferences.getBoolean("isAuthenticated", false);
    }

    public String getUsername() {
        return this.sharedPreferences.getString("username", "");
    }

    public void signOut() {
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        editor.remove("username");
        editor.remove("password");
        editor.remove("isAuthenticated");
        editor.commit();
    }

}
