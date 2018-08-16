package com.cinemojo.core.util;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.cinemojo.R;

public interface ViewUtils {

    static void showErrorSnackBar(Context context,  View rootView, String errorMessage) {
        Snackbar snackbar = Snackbar.make(rootView, errorMessage, Snackbar.LENGTH_SHORT);
        View view = snackbar.getView();
        view.setBackgroundColor(ContextCompat.getColor(context, R.color.error));

        TextView textView = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(ContextCompat.getColor(context, R.color.text_color_primary));
        textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
        snackbar.show();
    }
}
