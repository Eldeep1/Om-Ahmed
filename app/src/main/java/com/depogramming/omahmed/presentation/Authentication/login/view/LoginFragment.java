package com.depogramming.omahmed.presentation.Authentication.login.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.depogramming.omahmed.R;
import com.depogramming.omahmed.presentation.Authentication.login.presenter.LoginPresenter;
import com.depogramming.omahmed.presentation.Authentication.login.presenter.LoginPresenterImp;
import com.depogramming.omahmed.HomeActivity;
import com.depogramming.omahmed.utils.UserAlerts;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class LoginFragment extends Fragment implements LoginView {

    TextView singUpButton;
    TextInputEditText loginEmailEditText;
    TextInputEditText loginPasswordEditText;
    Button loginButton;
    Button loginGuestButton;
    MaterialButton loginGoogleButton;
    LottieAnimationView lottieAnimationView;
    FrameLayout lottieContainer;
    LoginPresenter loginPresenter;
    TextInputLayout passwordLayout;
    TextInputLayout emailLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginPresenter = new LoginPresenterImp();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        singUpButton = view.findViewById(R.id.signUpTextAction);
        loginEmailEditText = view.findViewById(R.id.loginEmailEditText);
        loginPasswordEditText = view.findViewById(R.id.loginPasswordEditText);
        loginButton = view.findViewById(R.id.loginButton);
        loginGuestButton = view.findViewById(R.id.loginGuestButton);
        loginGoogleButton = view.findViewById(R.id.loginGoogleButton);
        lottieAnimationView = view.findViewById(R.id.loginLottieAnimation);
        lottieContainer = view.findViewById(R.id.loginLoadingOverlay);
        passwordLayout = view.findViewById(R.id.loginPassword);
        emailLayout = view.findViewById(R.id.loginEmail);

        singUpButton.setOnClickListener(view1 -> Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registerFragment));
        loginGoogleButton.setOnClickListener(view1 -> googleLogin());
        loginButton.setOnClickListener(view1 -> login());
        loginGuestButton.setOnClickListener(view1 -> loginPresenter.guestLogin());

        return view;
    }

    private void googleLogin() {
        loginPresenter.googleAuth(getActivity());
    }

    private void login() {
        String email = Objects.requireNonNull(loginEmailEditText.getText()).toString();
        String password = Objects.requireNonNull(loginPasswordEditText.getText()).toString();

        loginPresenter.login(email, password);
    }

    @Override
    public void loginSuccess() {
        Intent intent = new Intent(getActivity(), HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        if (getActivity() != null) {
            getActivity().finish();
        }
    }

    @Override
    public void loginError(String errorMessage) {
        stopAnimation();
        UserAlerts.showSnackBar(getView(), errorMessage);
    }

    @Override
    public void loginLoading() {
        lottieAnimationView.playAnimation();
        lottieContainer.setVisibility(View.VISIBLE);
    }


    @Override
    public void guestLogin() {
        Intent intent = new Intent(getActivity(), HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void failedPasswordValidation(String errorMessage) {
        passwordLayout.setError(errorMessage);
    }

    @Override
    public void failedEmailValidation(String errorMessage) {
        emailLayout.setError(errorMessage);
    }

    @Override
    public void successPasswordValidation() {
        passwordLayout.setError(null);
    }

    @Override
    public void successEmailValidation() {
        emailLayout.setError(null);
    }


    private void stopAnimation() {
        lottieAnimationView.pauseAnimation();
        lottieContainer.setVisibility(View.GONE);
    }

    @Override
    public void onStart() {
        super.onStart();
        loginPresenter.setView(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        loginPresenter.clear();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (lottieAnimationView != null) {
            lottieAnimationView.cancelAnimation();
        }
    }
}