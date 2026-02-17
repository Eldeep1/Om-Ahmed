package com.depogramming.omahmed.presentation.Authentication.login.presenter;

import android.app.Activity;

import com.depogramming.omahmed.presentation.Authentication.login.view.LoginView;

public interface LoginPresenter {
    void login(String email,String password);
    void googleAuth(Activity activity);
    void clear();
    void guestLogin();
    void setView(LoginView loginView);
}
