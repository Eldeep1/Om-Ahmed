package com.depogramming.omahmed.presentation.splash.presenter;


import android.content.Context;

import com.depogramming.omahmed.data.onboarding.repository.UserPreferencesRepository;
import com.depogramming.omahmed.presentation.splash.view.SplashView;

public class SplashPresenterImp implements SplashPresenter {
    SplashView splashView;
    UserPreferencesRepository userPreferencesRepository;

    public SplashPresenterImp(SplashView splashView, Context context) {
        userPreferencesRepository = new UserPreferencesRepository(context);
        this.splashView = splashView;
    }

    @Override
    public void decideNextScreen() {
        //get data from shared pref and then decide where to go
        boolean finished = userPreferencesRepository.getOnBoardingFlag();
        if (finished) {
            splashView.navigateToLogin();
        } else {
            splashView.navigateToOnBoarding();
        }
    }
}
