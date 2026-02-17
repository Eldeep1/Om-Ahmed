package com.depogramming.omahmed.presentation.Authentication.register.view;

public interface RegisterView {
    void registerSuccess();

    void registerNameError(String errorMessage);
    void registerEmailError(String errorMessage);
    void registerPasswordError(String errorMessage);
    void registerNameSuccess();
    void registerEmailSuccess();
    void registerPasswordSuccess();
    void registerLoading();
    void registerError(String errorMessage);
}
