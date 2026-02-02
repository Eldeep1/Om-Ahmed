package com.depogramming.omahmed.presentation.onboarding.presenter;

import android.app.Application;
import android.content.Context;
import android.view.View;

import com.depogramming.omahmed.data.onboarding.repository.UserPreferencesRepository;
import com.depogramming.omahmed.presentation.onboarding.views.OnBoardingView;

public class OnBoardingScreenPresenterImp implements OnBoardingPresenter {
    OnBoardingView onBoardingView;
    UserPreferencesRepository userPreferencesRepository;

    public OnBoardingScreenPresenterImp(OnBoardingView onBoardingView, Context context) {
        this.onBoardingView = onBoardingView;
        userPreferencesRepository = new UserPreferencesRepository(context);
    }

    @Override
    public void nextButtonClick() {
        onBoardingView.nextButton();
    }

    @Override
    public void endButtonClick(View view, Application application) {
        onBoardingView.finishButton(view);
        userPreferencesRepository.setOnBoardingFlag(true);
    }
}
