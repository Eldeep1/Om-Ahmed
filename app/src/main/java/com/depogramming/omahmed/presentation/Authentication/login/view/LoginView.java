package com.depogramming.omahmed.presentation.Authentication.login.view;

public interface LoginView {
    void loginSuccess();
    void loginError(String errorMessage);
    void loginLoading();
    void guestLogin();
    void failedPasswordValidation(String errorMessage);
    void failedEmailValidation(String errorMessage);
    void successPasswordValidation();
    void successEmailValidation();
}
