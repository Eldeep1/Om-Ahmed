package com.depogramming.omahmed.data.auth.register.datasource;

import com.depogramming.omahmed.data.auth.register.model.RegisterUserDTO;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;

import io.reactivex.rxjava3.core.Single;

public class FirebaseDataSource {
    private FirebaseAuth mAuth;

    public FirebaseDataSource() {
        mAuth = FirebaseAuth.getInstance();

    }
    public Single<AuthResult> register(RegisterUserDTO registerUserDTO) {
        return Single.create(emitter -> {
            mAuth.createUserWithEmailAndPassword(registerUserDTO.getEmail(), registerUserDTO.getPassword())
                    .addOnSuccessListener(authResult -> {
                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                .setDisplayName(registerUserDTO.getName())
                                .build();
                        if (!emitter.isDisposed()) emitter.onSuccess(authResult);
                    })
                    .addOnFailureListener(e -> {
                        if (!emitter.isDisposed()) emitter.onError(e);
                    });
        });
    }
}
