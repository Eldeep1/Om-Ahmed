package com.depogramming.omahmed.presentation.Authentication.login.presenter;

import android.app.Activity;

public interface LoginPresenter {
    void login(String email,String password);
    void googleAuth(Activity activity);
}
