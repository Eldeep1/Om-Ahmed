package com.depogramming.omahmed.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.depogramming.omahmed.R;
import com.google.android.material.button.MaterialButton;

public class ActionCheckingDialogue {
    public static void show(Context context,String title, String description, int imageID,DialogCallback dialogCallback) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Context themedContext = new ContextThemeWrapper(context, R.style.Theme_OmAhmed);
        View view = LayoutInflater.from(themedContext).inflate(R.layout.action_checking_dialoug, null);
        dialog.setContentView(view);
        dialog.setContentView(view);
        TextView actionDialogueTitle = view.findViewById(R.id.actionDialogueTitle);
        TextView actionDialogueDescription = view.findViewById(R.id.actionDialogueDescription);
        ImageView actionDialogueImage = view.findViewById(R.id.actionDialogueImage);
        MaterialButton actionDialogueRemoveButton = view.findViewById(R.id.actionDialogueRemoveButton);
        MaterialButton actionDialogueKeepButton = view.findViewById(R.id.actionDialogueKeepButton);
        actionDialogueTitle.setText(title);
        actionDialogueDescription.setText(description);
        actionDialogueImage.setImageResource(imageID);
        dialog.show();
        actionDialogueRemoveButton.setOnClickListener(v -> {
            dialogCallback.onAction(true);
            dialog.dismiss();
        });
        actionDialogueKeepButton.setOnClickListener(v -> {
            dialogCallback.onAction(false);
            dialog.dismiss();
        });
    }
}
