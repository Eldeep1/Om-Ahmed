package com.depogramming.omahmed.data.auth.register;

import android.app.Activity;
import android.content.Context;

import com.depogramming.omahmed.data.auth.register.datasource.FirebaseDataSource;
import com.depogramming.omahmed.data.auth.register.model.RegisterUserDTO;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import io.reactivex.rxjava3.core.Single;

public class RegisterRepo {
    FirebaseDataSource firebaseDataSource;
    ;

    public RegisterRepo() {
        firebaseDataSource= new FirebaseDataSource();
    }
    public Single<AuthResult> register(RegisterUserDTO registerUserDTO){
        return firebaseDataSource.register(registerUserDTO);
    }
}
