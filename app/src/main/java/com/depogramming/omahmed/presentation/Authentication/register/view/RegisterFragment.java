package com.depogramming.omahmed.presentation.Authentication.register.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.depogramming.omahmed.R;
import com.depogramming.omahmed.presentation.Authentication.register.presenter.RegisterPresenter;
import com.depogramming.omahmed.presentation.Authentication.register.presenter.RegisterPresenterImp;
import com.depogramming.omahmed.presentation.home.view.HomeActiivity;
import com.depogramming.omahmed.utils.UserAlerts;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterFragment extends Fragment implements RegisterView {
    Button registerButton;
    TextInputEditText signUpFullNameEditText;
    TextInputEditText signUpEmailEditText;
    TextInputEditText signUpPasswordEditText;
    RegisterPresenter registerPresenter;
    LottieAnimationView lottieAnimationView;
    FrameLayout pageConstraintLayout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerPresenter= new RegisterPresenterImp(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        registerButton=view.findViewById(R.id.registerButton);
        signUpFullNameEditText=view.findViewById(R.id.signUpFullNameEditText);
        signUpEmailEditText=view.findViewById(R.id.signUpEmailEditText);
        signUpPasswordEditText=view.findViewById(R.id.signUpPasswordEditText);
        lottieAnimationView=view.findViewById(R.id.registerLottieAnimation);
        pageConstraintLayout=view.findViewById(R.id.registerLoadingOverlay);

        registerButton.setOnClickListener(view1 -> register());
        return view;
    }

    private void register() {
        String name=signUpFullNameEditText.getText().toString();
        String email=signUpEmailEditText.getText().toString();
        String password=signUpPasswordEditText.getText().toString();

        registerPresenter.register(name,email,password);
    }


    @Override
    public void registerSuccess() {
        Intent intent = new Intent(getActivity(), HomeActiivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        if (getActivity() != null) {
            getActivity().finish();
        }
    }
    @Override
    public void registerError(String errorMessage) {
        stopAnimation();
        UserAlerts.showSnackBar(getView(),errorMessage);
    }

    @Override
    public void registerLoading() {
        lottieAnimationView.playAnimation();
        pageConstraintLayout.setVisibility(View.VISIBLE);
        pageConstraintLayout.setClickable(false);
    }

    @Override
    public void validationFailed(String errorMessage) {
        stopAnimation();
        UserAlerts.showSnackBar(getView(),errorMessage);
    }
    private void stopAnimation(){
        lottieAnimationView.pauseAnimation();
        pageConstraintLayout.setVisibility(View.GONE);
        pageConstraintLayout.setClickable(true);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (lottieAnimationView != null) {
            lottieAnimationView.cancelAnimation();
        }
    }
}