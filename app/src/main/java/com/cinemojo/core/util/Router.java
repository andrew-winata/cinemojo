package com.cinemojo.core.util;

import android.content.Context;
import android.content.Intent;

import com.cinemojo.home.HomeActivity;
import com.cinemojo.profile.ProfileActivity;
import com.cinemojo.sign_in.SignInActivity;

public interface Router {

    static void goToSignIn(Context context) {
        Intent intent = new Intent(context, SignInActivity.class);
        context.startActivity(intent);
    }

    static void goToHome(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);
    }

    static void goToProfile(Context context) {
        Intent intent = new Intent(context, ProfileActivity.class);
        context.startActivity(intent);
    }
}
