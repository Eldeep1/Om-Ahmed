package com.depogramming.omahmed.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.depogramming.omahmed.MainActivity;
import com.depogramming.omahmed.R;
import com.google.android.material.button.MaterialButton;
public class GuestModeDialog {
    public static void show(Context context) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Context themedContext = new ContextThemeWrapper(context, R.style.Theme_OmAhmed);
        View view = LayoutInflater.from(themedContext).inflate(R.layout.dialog_guest_prompt, null);
        dialog.setContentView(view);
        dialog.setContentView(view);

        TextView dialogMessage = view.findViewById(R.id.dialogMessage);
        MaterialButton loginButton = view.findViewById(R.id.loginButton);
        MaterialButton continueAsGuestButton = view.findViewById(R.id.continueAsGuestButton);

        dialogMessage.setText(R.string.you_must_be_logged_in_to_access_this_feature);


        loginButton.setOnClickListener(v -> {
            dialog.dismiss();
            Intent intent = new Intent(context.getApplicationContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra("SKIP_SPLASH", true);
            context.startActivity(intent);

        });

        continueAsGuestButton.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }
}
