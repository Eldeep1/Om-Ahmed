package com.depogramming.omahmed.presentation.profile.presentation;

import com.depogramming.omahmed.data.auth.repository.AuthRepo;
import com.depogramming.omahmed.presentation.profile.view.ProfileView;

public class ProfilePresenterImp implements ProfilePresenter {
    ProfileView profileView;
    AuthRepo authRepo;
    public ProfilePresenterImp(ProfileView profileView){
        this.profileView=profileView;
        authRepo = new AuthRepo();
    }
    @Override
    public void onUploadClick() {
        //1. upload data to firestore
        //2. show something to the user
    }

    @Override
    public void onDownloadClick() {
        //1. download data from firestore
        //2. show something to the user
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
