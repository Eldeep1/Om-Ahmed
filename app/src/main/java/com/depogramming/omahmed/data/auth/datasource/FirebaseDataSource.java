package com.depogramming.omahmed.data.auth.datasource;

import android.app.Activity;
import android.os.CancellationSignal;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.credentials.Credential;
import androidx.credentials.CredentialManager;
import androidx.credentials.CredentialManagerCallback;
import androidx.credentials.CustomCredential;
import androidx.credentials.GetCredentialRequest;
import androidx.credentials.GetCredentialResponse;
import androidx.credentials.exceptions.GetCredentialException;

import com.depogramming.omahmed.R;
import com.depogramming.omahmed.data.auth.login.model.LoginUserDTO;
import com.depogramming.omahmed.data.auth.register.model.RegisterUserDTO;
import com.google.android.libraries.identity.googleid.GetGoogleIdOption;
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.function.Consumer;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;

public class FirebaseDataSource {
    private final FirebaseAuth mAuth;
    private CredentialManager credentialManager;
    private static final String TAG = "FirebaseDataSource";
    private static final String TYPE_GOOGLE_ID_TOKEN_CREDENTIAL =
            GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL;

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

    public Single<AuthResult> login(LoginUserDTO loginUserDTO) {
        return Single.create(emitter -> mAuth.signInWithEmailAndPassword(loginUserDTO.getEmail(), loginUserDTO.getPassword())
                .addOnSuccessListener(authResult -> emitter.onSuccess(authResult))
                .addOnFailureListener(e -> emitter.onError(e)));

    }

    private void handleGoogleSignIn(
            Credential credential,
            SingleEmitter<FirebaseUser> emitter
    ) {
        if (!(credential instanceof CustomCredential)) {
            emitter.onError(new Exception("Unsupported credential type"));
            return;
        }

        CustomCredential customCredential = (CustomCredential) credential;

        if (!TYPE_GOOGLE_ID_TOKEN_CREDENTIAL.equals(customCredential.getType())) {
            emitter.onError(new Exception("Not a Google ID credential"));
            return;
        }

        GoogleIdTokenCredential googleIdTokenCredential =
                GoogleIdTokenCredential.createFrom(customCredential.getData());

        firebaseAuthWithGoogle(
                googleIdTokenCredential.getIdToken(),
                user -> {
                    if (!emitter.isDisposed()) emitter.onSuccess(user);
                },
                error -> {
                    if (!emitter.isDisposed()) emitter.onError(error);
                }
        );
    }

    public Single<FirebaseUser> signInWithGoogle(Activity activity) {
        return Single.create(emitter -> {

            credentialManager = CredentialManager.create(activity);

            GetGoogleIdOption googleIdOption = new GetGoogleIdOption.Builder()
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(
                            activity.getString(R.string.default_web_client_id)
                    )
                    .build();

            GetCredentialRequest request = new GetCredentialRequest.Builder()
                    .addCredentialOption(googleIdOption)
                    .build();

            credentialManager.getCredentialAsync(
                    activity,
                    request,
                    new CancellationSignal(),
                    ContextCompat.getMainExecutor(activity),
                    new CredentialManagerCallback<>() {
                        @Override
                        public void onResult(GetCredentialResponse result) {
                            handleGoogleSignIn(result.getCredential(), emitter);
                        }

                        @Override
                        public void onError(@NonNull GetCredentialException e) {
                            Log.e(TAG, "Credential error", e);

                            if (!emitter.isDisposed()) {
                                emitter.onError(e);
                            }
                        }
                    }
            );
        });
    }

    private void firebaseAuthWithGoogle(
            String idToken,
            Consumer<FirebaseUser> onSuccess,
            Consumer<Exception> onError
    ) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);

        mAuth.signInWithCredential(credential)
                .addOnSuccessListener(result -> onSuccess.accept(result.getUser()))
                .addOnFailureListener(onError::accept);
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

    public void logout(){
        mAuth.signOut();
    }

    public String getUserName() {
        return mAuth.getCurrentUser().getDisplayName();
    }
}
