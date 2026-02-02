package com.depogramming.omahmed.presentation.onboarding.presenter;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import com.depogramming.omahmed.presentation.onboarding.views.OnBoardingView;

public class OnBoardingScreenPresenterImp implements OnBoardingPresenter {
    OnBoardingView onBoardingView;

    public OnBoardingScreenPresenterImp(OnBoardingView onBoardingView) {
        this.onBoardingView = onBoardingView;
    }

    @Override
    public void nextButtonClick() {
        onBoardingView.nextButton();
    }

    @Override
    public void endButtonClick(View view, Application application) {
        onBoardingView.finishButton(view);
        SharedPreferences sharedPreferences = application.getApplicationContext().getSharedPreferences("onBoarding", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("onBoardingDone", true);
        editor.apply();
    }
}
