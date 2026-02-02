package com.depogramming.omahmed.presentation.onboarding.views;

import android.view.View;

import androidx.navigation.Navigation;

import com.depogramming.omahmed.R;

public interface OnBoardingView {
    default void finishButton(View view) {
        Navigation.findNavController(view).navigate(R.id.action_veiwPagerFragment_to_loginFragment);
    }

    void nextButton();
}
