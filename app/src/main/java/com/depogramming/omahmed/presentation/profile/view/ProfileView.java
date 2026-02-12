package com.depogramming.omahmed.presentation.profile.view;

public interface ProfileView {
    void onUploadClick();
    void onUploadClickAction();
    void onDownloadClick();
    void onDownloadClickAction();

    void onLogoutClick();

    void onLogoutClickAction();
    void setName(String name);
}
