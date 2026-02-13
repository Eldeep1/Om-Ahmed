package com.depogramming.omahmed.presentation.profile.presentation;

import android.content.Context;

import com.depogramming.omahmed.presentation.profile.view.ProfileView;

public interface ProfilePresenter {
    void onUploadClick();
    void onDownloadClick();
    void onLogoutClick(Context context);
    void getData();
    void clear();

    void setView(ProfileView profileView);
}
