package com.depogramming.omahmed.presentation.Authentication.login.view;

public interface LoginView {
    void loginSuccess();
    void loginError(String errorMessage);

    void loginLoading();
    void validationFailed(String errorMessage);
    public void guestLogin();
}
