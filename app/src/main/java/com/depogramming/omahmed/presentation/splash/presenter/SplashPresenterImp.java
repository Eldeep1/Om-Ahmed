package com.depogramming.omahmed.presentation.splash.presenter;


import android.content.Context;

import com.depogramming.omahmed.data.auth.register.AuthRepo;
import com.depogramming.omahmed.data.onboarding.repository.UserPreferencesRepository;
import com.depogramming.omahmed.presentation.splash.view.SplashView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SplashPresenterImp implements SplashPresenter {
    SplashView splashView;
    UserPreferencesRepository userPreferencesRepository;
    AuthRepo authRepo;

    public SplashPresenterImp(SplashView splashView, Context context) {
        userPreferencesRepository = new UserPreferencesRepository(context);
        authRepo = new AuthRepo();
        this.splashView = splashView;
    }

    @Override
    public void decideNextScreen() {
        boolean finished = userPreferencesRepository.getOnBoardingFlag();
        if (finished) {
            //TODO: CompositeDisposable again...
            Disposable subscribe = authRepo.getRegisteredUser()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(firebaseUser -> splashView.navigateToHome(), throwable -> splashView.navigateToLogin());
        } else {
            splashView.navigateToOnBoarding();
        }
    }
}
