package com.depogramming.omahmed.presentation.splash.presenter;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.depogramming.omahmed.presentation.splash.view.SplashView;
import com.depogramming.omahmed.utils.Consts;

public class SplashPresenterImp implements SplashPresenter {
    SplashView splashView;
    private final SharedPreferences sharedPreferences;

    public SplashPresenterImp(SplashView splashView, Application application) {
        sharedPreferences = application.getApplicationContext().getSharedPreferences(Consts.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        this.splashView = splashView;
    }

    @Override
    public void decideNextScreen() {
        //get data from shared pref and then decide where to go
        boolean finished = sharedPreferences.getBoolean(Consts.ON_BOARDING_FLAG, false);
        if (finished) {
            splashView.navigateToLogin();
        } else {
            splashView.navigateToOnBoarding();
        }
    }
}
