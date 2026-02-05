package com.depogramming.omahmed.presentation.Authentication.register.presenter;

import android.app.Activity;

public interface RegisterPresenter {
    void register(String fullName,String email, String password);
    void googleAuth(Activity activity);
}
