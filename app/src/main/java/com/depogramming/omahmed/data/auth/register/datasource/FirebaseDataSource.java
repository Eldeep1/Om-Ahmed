package com.depogramming.omahmed.data.auth.register.datasource;

import com.depogramming.omahmed.data.auth.register.model.RegisterUserDTO;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;

public class FirebaseDataSource {
    private final FirebaseAuth mAuth;

    public FirebaseDataSource() {
        mAuth = FirebaseAuth.getInstance();

    }

    //TODO: maybe decouple that large function to two small function, one responsible registration and the other for updating display name
    public Single<AuthResult> register(RegisterUserDTO registerUserDTO) {
        return Single.create(emitter -> mAuth.createUserWithEmailAndPassword(registerUserDTO.getEmail(), registerUserDTO.getPassword())
                .addOnSuccessListener(authResult -> {
                    FirebaseUser user = authResult.getUser();

                    if (user != null) {
                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                .setDisplayName(registerUserDTO.getName())
                                .build();

                        user.updateProfile(profileUpdates)
                                .addOnCompleteListener(task -> {
                                    if (task.isSuccessful()) {
                                        if (!emitter.isDisposed())
                                            emitter.onSuccess(authResult);
                                    } else {
                                        if (!emitter.isDisposed())
                                            emitter.onError(task.getException());
                                    }
                                });
                    }
                })
                .addOnFailureListener(e -> {
                    if (!emitter.isDisposed()) emitter.onError(e);
                }));
    }

    public Maybe<FirebaseUser> getRegisteredUser() {
        return Maybe.create(emitter -> {
            FirebaseUser user = mAuth.getCurrentUser();
            if (user != null) {
                emitter.onSuccess(user);
            } else {
                emitter.onComplete();
            }
        });
    }
}
