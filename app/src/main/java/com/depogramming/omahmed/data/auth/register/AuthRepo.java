package com.depogramming.omahmed.data.auth.register;

import android.app.Activity;
import android.app.Application;

import com.depogramming.omahmed.data.auth.login.LoginUserDTO;
import com.depogramming.omahmed.data.auth.register.datasource.FirebaseDataSource;
import com.depogramming.omahmed.data.auth.register.model.RegisterUserDTO;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

public class AuthRepo {
    FirebaseDataSource firebaseDataSource;
    public AuthRepo() {
        firebaseDataSource= new FirebaseDataSource();
    }

    public Single<AuthResult> register(RegisterUserDTO registerUserDTO){
        return firebaseDataSource.register(registerUserDTO);
    }
    public Single<AuthResult> login(LoginUserDTO loginUserDTO){
        return firebaseDataSource.login(loginUserDTO);
    }
    public Maybe<FirebaseUser> getRegisteredUser(){
        return firebaseDataSource.getRegisteredUser();
    }
    public Single<FirebaseUser> googleAuth(Activity activity){
       return firebaseDataSource.signInWithGoogle(activity);
    }
}
