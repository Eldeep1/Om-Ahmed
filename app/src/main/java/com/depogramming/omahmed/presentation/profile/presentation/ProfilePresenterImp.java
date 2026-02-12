package com.depogramming.omahmed.presentation.profile.presentation;

import android.content.Context;

import com.depogramming.omahmed.data.auth.repository.AuthRepo;
import com.depogramming.omahmed.data.syncing.repo.SyncingRepo;
import com.depogramming.omahmed.presentation.profile.view.ProfileView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ProfilePresenterImp implements ProfilePresenter {
    ProfileView profileView;
    AuthRepo authRepo;
    SyncingRepo syncingRepo;
    public ProfilePresenterImp(ProfileView profileView, Context context){
        this.profileView=profileView;
        authRepo = new AuthRepo();
        syncingRepo = new SyncingRepo(context);
    }
    @Override
    public void onUploadClick() {

        Disposable disposable = syncingRepo.uploadAllUsersData().
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(
                        () -> profileView.onUploadClickAction(),
                        throwable -> System.out.println("Interesting")
                );

    }

    @Override
    public void onDownloadClick() {
        Disposable disposable = syncingRepo.downloadAllUsersData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> profileView.onDownloadClickAction(),
                        throwable -> {
                            System.out.println("wtf");
                            throwable.printStackTrace();
                        }
                );
    }

    @Override
    public void onLogoutClick() {
        authRepo.logout();
        profileView.onLogoutClickAction();
    }
    @Override
    public void getData() {
    }
}
