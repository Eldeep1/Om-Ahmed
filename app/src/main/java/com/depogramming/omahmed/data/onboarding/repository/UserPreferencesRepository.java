package com.depogramming.omahmed.data.onboarding.repository;


import android.content.Context;
import com.depogramming.omahmed.data.onboarding.datasource.local.SharedPreferencesDataSource;

public class UserPreferencesRepository {
    SharedPreferencesDataSource sharedPreferencesDataSource;

    public UserPreferencesRepository(Context context) {
        sharedPreferencesDataSource = new SharedPreferencesDataSource(context);
    }

    public boolean getOnBoardingFlag(){
        return sharedPreferencesDataSource.getOnBoardingFlag();
    }
    public void setOnBoardingFlag(boolean value){
        sharedPreferencesDataSource.setOnBoardingFlag(value);
    }
}
