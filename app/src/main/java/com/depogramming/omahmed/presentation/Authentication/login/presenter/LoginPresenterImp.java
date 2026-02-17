package com.depogramming.omahmed.presentation.Authentication.login.presenter;

import android.app.Activity;

import com.depogramming.omahmed.data.auth.model.LoginUserDTO;
import com.depogramming.omahmed.data.auth.repository.AuthRepo;
import com.depogramming.omahmed.presentation.Authentication.login.view.LoginView;
import com.depogramming.omahmed.utils.UserData;
import com.google.firebase.auth.FirebaseUser;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LoginPresenterImp implements LoginPresenter {
    AuthRepo authRepo;
    private final CompositeDisposable disposables = new CompositeDisposable();
    LoginView loginView;

    public LoginPresenterImp() {
        authRepo = new AuthRepo();
    }

    @Override
    public void login(String email, String password) {
        LoginUserDTO loginUserDTO = new LoginUserDTO(email, password);

        if (validateUser(loginUserDTO)) {
            loginView.loginLoading();
            disposables.add(authRepo.login(loginUserDTO)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(authResult -> {
                                UserData.isGuest = false;
                                loginView.loginSuccess();
                            },
                            throwable -> loginView.loginError(throwable.getMessage())));
        }

    }

    private boolean validateUser(LoginUserDTO loginUserDTO) {

        if (loginUserDTO.getEmail().trim().isEmpty()) {
            loginView.failedEmailValidation("Email address is required.");
            return false;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(loginUserDTO.getEmail()).matches()) {
            loginView.failedEmailValidation("Please enter a valid email address.");
            return false;
        } else {
            loginView.successEmailValidation();
        }
        if (loginUserDTO.getPassword().isEmpty()) {
            loginView.failedPasswordValidation("Password cannot be empty.");
            return false;
        }
        if (loginUserDTO.getPassword().length() < 6) {
            loginView.failedPasswordValidation("Password must be at least 6 characters.");
            return false;
        }
        loginView.successPasswordValidation();
        return true;
    }

    @Override
    public void googleAuth(Activity activity) {
        UserData.isGuest = false;
        loginView.loginLoading();
        authRepo.googleAuth(activity).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposables.add(d);

            }

            @Override
            public void onSuccess(FirebaseUser firebaseUser) {
                UserData.isGuest = false;
                loginView.loginSuccess();
            }

            @Override
            public void onError(Throwable e) {
                loginView.loginError(e.getMessage());
            }
        });

    }

    @Override
    public void guestLogin() {
        UserData.isGuest = true;
        loginView.guestLogin();
    }

    @Override
    public void setView(LoginView loginView) {
        this.loginView = loginView;
    }

    public void clear() {
        disposables.clear();
        loginView = null;
    }
}
