package com.cinemojo.core.util;

import android.content.Context;
import android.content.Intent;

import com.cinemojo.login.LoginActivity;

public interface Router {

    static void goToLogin(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
}
