package com.depogramming.omahmed.presentation.Authentication.login.presenter;

import android.app.Activity;

import com.depogramming.omahmed.data.auth.login.model.LoginUserDTO;
import com.depogramming.omahmed.data.auth.repository.AuthRepo;
import com.depogramming.omahmed.presentation.Authentication.login.view.LoginView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LoginPresenterImp implements LoginPresenter {
    AuthRepo authRepo;

    LoginView loginView;

    public LoginPresenterImp(LoginView loginView) {
        authRepo = new AuthRepo();
        this.loginView = loginView;
    }

    @Override
    public void login(String email, String password) {
        LoginUserDTO loginUserDTO = new LoginUserDTO(email, password);

        if(validateUser(loginUserDTO)){
            loginView.loginLoading();
            Disposable subscribe = authRepo.login(loginUserDTO)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(authResult -> loginView.loginSuccess(),
                            throwable -> loginView.loginError(throwable.getMessage()));
        }

    }

    private boolean validateUser(LoginUserDTO loginUserDTO) {

        if (loginUserDTO.getEmail().trim().isEmpty()) {
            loginView.validationFailed("Email address is required.");
            return false;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(loginUserDTO.getEmail()).matches()) {
            loginView.validationFailed("Please enter a valid email address.");
            return false;
        }
        if (loginUserDTO.getPassword().isEmpty()) {
            loginView.validationFailed("Password cannot be empty.");
            return false;
        }
        if (loginUserDTO.getPassword().length() < 6) {
            loginView.validationFailed("Password must be at least 6 characters.");
            return false;
        }
        return true;
    }

    @Override
    public void googleAuth(Activity activity) {

    }
}
