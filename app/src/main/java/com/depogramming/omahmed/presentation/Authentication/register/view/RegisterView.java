package com.depogramming.omahmed.presentation.Authentication.register.view;

import com.depogramming.omahmed.data.auth.register.model.RegisterUserDTO;

public interface RegisterView {
    void registerSuccess();

    void registerError(String errorMessage);

    void registerLoading();
    void validationFailed(String errorMessage);
}
