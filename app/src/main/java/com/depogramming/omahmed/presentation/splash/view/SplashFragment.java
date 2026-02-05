package com.depogramming.omahmed.presentation.splash.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.depogramming.omahmed.R;
import com.depogramming.omahmed.presentation.home.view.HomeActiivity;
import com.depogramming.omahmed.presentation.splash.presenter.SplashPresenter;
import com.depogramming.omahmed.presentation.splash.presenter.SplashPresenterImp;

public class SplashFragment extends Fragment implements SplashView {

    private SplashPresenter presenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        presenter = new SplashPresenterImp(this, requireActivity().getApplication());
        View view = inflater.inflate(R.layout.fragment_splash, container, false);
        setupAnimation(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new Handler().postDelayed(() -> presenter.decideNextScreen(), 4000);
    }

    private void setupAnimation(View view) {
        TextView text = view.findViewById(R.id.splashText);
        text.setTranslationY(50f);
        text.animate()
                .alpha(1f)
                .translationY(0f)
                .setDuration(800)
                .setStartDelay(1000)
                .start();
    }

    @Override
    public void navigateToLogin() {
        Navigation.findNavController(requireView()).navigate(R.id.action_splashFragment_to_loginFragment);
    }

    @Override
    public void navigateToOnBoarding() {
        Navigation.findNavController(requireView()).navigate(R.id.action_splashFragment_to_veiwPagerFragment);
    }

    @Override
    public void navigateToHome() {
        Intent intent = new Intent(getActivity(), HomeActiivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        if (getActivity() != null) {
            getActivity().finish();
        }
    }
}