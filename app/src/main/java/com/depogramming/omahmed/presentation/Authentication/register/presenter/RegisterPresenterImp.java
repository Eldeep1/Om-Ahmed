package com.depogramming.omahmed.presentation.Authentication.register.presenter;

import android.app.Activity;
import androidx.annotation.NonNull;
import com.depogramming.omahmed.data.auth.register.AuthRepo;
import com.depogramming.omahmed.data.auth.register.model.RegisterUserDTO;
import com.depogramming.omahmed.presentation.Authentication.register.view.RegisterView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RegisterPresenterImp implements RegisterPresenter {
    RegisterView registerView;

    AuthRepo authRepo;

    public RegisterPresenterImp(RegisterView registerView) {
        authRepo = new AuthRepo();
        this.registerView=registerView;
    }

    public void googleAuth(Activity activity) {
        registerView.registerLoading();
        authRepo.googleAuth(activity).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<>() {
            @Override
            public void onSubscribe(Disposable d) {
                //TODO:
                // add 'd' to a CompositeDisposable to prevent memory leaks
            }

            @Override
            public void onSuccess(FirebaseUser firebaseUser) {
                registerView.registerSuccess();
            }

            @Override
            public void onError(Throwable e) {
                registerView.registerError(e.getMessage());
            }
        });
    }

    public void register(String fullName, String email, String password) {
        RegisterUserDTO userDTO = new RegisterUserDTO(fullName, email, password);

        if (validateUser(userDTO)) {
            registerView.registerLoading();

            authRepo.register(userDTO)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            //TODO:
                            // add 'd' to a CompositeDisposable to prevent memory leaks
                        }

                        @Override
                        public void onSuccess(AuthResult authResult) {
                            registerView.registerSuccess();
                        }

                        @Override
                        public void onError(Throwable e) {
                            registerView.registerError(e.getMessage());
                        }
                    });
        }
    }
    private boolean validateUser(@NonNull RegisterUserDTO userDTO) {
        if (userDTO.getName().trim().isEmpty()) {
            registerView.validationFailed("Please enter your full name.");
            return false;
        }
        if (userDTO.getEmail().trim().isEmpty()) {
            registerView.validationFailed("Email address is required.");
            return false;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(userDTO.getEmail()).matches()) {
            registerView.validationFailed("Please enter a valid email address.");
            return false;
        }
        if (userDTO.getPassword().isEmpty()) {
            registerView.validationFailed("Password cannot be empty.");
            return false;
        }
        if (userDTO.getPassword().length() < 6) {
            registerView.validationFailed("Password must be at least 6 characters.");
            return false;
        }
        return true;
    }
}
