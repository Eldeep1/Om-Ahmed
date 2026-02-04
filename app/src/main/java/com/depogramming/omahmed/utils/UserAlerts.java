package com.depogramming.omahmed.utils;

import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class UserAlerts {
    public static void showSnackBar(View view, String message){
        Snackbar.make(view,message, Snackbar.LENGTH_LONG).show();
    }
}
