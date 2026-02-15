package com.depogramming.omahmed.presentation.splash.presenter;


import android.content.Context;
import android.util.Log;

import com.depogramming.omahmed.data.auth.repository.AuthRepo;
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
                    .doOnSubscribe(d -> Log.d("RxDebug", "Started checking user..."))
                    .doOnTerminate(() -> Log.d("RxDebug", "Chain terminated"))
                    .subscribe(
                            firebaseUser -> {
                                Log.d("RxDebug", "User found: " + firebaseUser.getUid());
                                Log.d("RxDebug", "User found: " + firebaseUser.getEmail());
                                Log.d("RxDebug", "User found: " + firebaseUser.getDisplayName());
                                splashView.navigateToHome();
                            },
                            throwable -> {
                                Log.e("RxDebug", "Error occurred", throwable);
                                splashView.navigateToLogin();
                            },
                            () -> {
                                // This runs if the Observable completes WITHOUT emitting a user
                                Log.d("RxDebug", "Completed with NO user (Empty)");
                                splashView.navigateToLogin();
                            }
                    );
        } else {
            splashView.navigateToOnBoarding();
        }
    }
}
