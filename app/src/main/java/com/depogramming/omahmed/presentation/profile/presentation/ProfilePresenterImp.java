package com.depogramming.omahmed.presentation.profile.presentation;

public class ProfilePresenterImp implements ProfilePresenter {
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
        //1. just call the firebase logout function
        //2. navigate to login page
    }

    @Override
    public void getData() {
        //call firebase to get the user's name
    }
}
