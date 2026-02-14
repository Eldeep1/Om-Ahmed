package com.depogramming.omahmed.presentation.profile.presentation;

import android.content.Context;

import com.depogramming.omahmed.data.auth.repository.AuthRepo;
import com.depogramming.omahmed.data.syncing.repo.SyncingRepo;
import com.depogramming.omahmed.presentation.profile.view.ProfileView;
import com.depogramming.omahmed.utils.UserAlerts;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ProfilePresenterImp implements ProfilePresenter {
    ProfileView profileView;
    AuthRepo authRepo;
    SyncingRepo syncingRepo;
    private final CompositeDisposable disposables = new CompositeDisposable();

    public ProfilePresenterImp( Context context) {
        authRepo = new AuthRepo();
        syncingRepo = new SyncingRepo(context);
    }

    @Override
    public void onUploadClick() {

        disposables.add(syncingRepo.uploadAllUsersData().
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(
                        () -> profileView.onUploadClickAction(),
                        throwable -> System.out.println("Interesting")
                ));

    }

    @Override
    public void onDownloadClick() {
        disposables.add(syncingRepo.downloadAllUsersData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> profileView.onDownloadClickAction()
                ));
    }

    @Override
    public void onLogoutClick(Context context) {
        new Thread(() -> authRepo.logout(context)).start();
        profileView.onLogoutClickAction();
    }

    @Override
    public void getData() {
        String userName = authRepo.getUserName();
        profileView.setName(userName);
    }
    @Override
    public void clear() {
        disposables.clear();
        profileView = null;
    }
    @Override
    public void setView(ProfileView profileView){
        this.profileView=profileView;
    }
}
