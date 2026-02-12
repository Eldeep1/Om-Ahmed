package com.depogramming.omahmed.presentation.profile.presentation;

import android.content.Context;

public interface ProfilePresenter {
    void onUploadClick();
    void onDownloadClick();
    void onLogoutClick(Context context);
    void getData();
    void clear();
}
