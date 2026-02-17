package com.depogramming.omahmed.presentation.Authentication.register.presenter;

import android.app.Activity;
import androidx.annotation.NonNull;
import com.depogramming.omahmed.data.auth.repository.AuthRepo;
import com.depogramming.omahmed.data.auth.model.RegisterUserDTO;
import com.depogramming.omahmed.presentation.Authentication.register.view.RegisterView;
import com.depogramming.omahmed.utils.UserData;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RegisterPresenterImp implements RegisterPresenter {
    RegisterView registerView;
    private final CompositeDisposable disposables = new CompositeDisposable();
    AuthRepo authRepo;

    public RegisterPresenterImp(RegisterView registerView) {
        authRepo = new AuthRepo();
        this.registerView=registerView;
    }

    public void googleAuth(Activity activity) {
        UserData.isGuest=false;
        registerView.registerLoading();
        authRepo.googleAuth(activity).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposables.add(d);
            }

            @Override
            public void onSuccess(FirebaseUser firebaseUser) {
                UserData.isGuest=false;
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
                            disposables.add(d);
                        }

                        @Override
                        public void onSuccess(AuthResult authResult) {
                            registerView.registerSuccess();
                            UserData.isGuest=false;
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
            registerView.registerNameError("Please enter your full name.");
            return false;
        }
        else{
            registerView.registerNameSuccess();
        }
        if (userDTO.getEmail().trim().isEmpty()) {
            registerView.registerEmailError("Email address is required.");
            return false;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(userDTO.getEmail()).matches()) {
            registerView.registerEmailError("Please enter a valid email address.");
            return false;
        }
        else{
            registerView.registerEmailSuccess();
        }
        if (userDTO.getPassword().isEmpty()) {
            registerView.registerPasswordError("Password cannot be empty.");
            return false;
        }
        if (userDTO.getPassword().length() < 6) {
            registerView.registerPasswordError("Password must be at least 6 characters.");
            return false;
        }
        registerView.registerPasswordSuccess();
        return true;
    }
    @Override
    public void clear() {
        disposables.clear();
        registerView = null;
    }
}
