package com.depogramming.omahmed.data.onboarding.datasource.local;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.depogramming.omahmed.utils.Consts;

public class SharedPreferencesDataSource {
    private final SharedPreferences sharedPreferences;
    Context context;
    public SharedPreferencesDataSource(Context context) {
        this.context=context;
        sharedPreferences = context.getApplicationContext().getSharedPreferences(Consts.SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }
    public boolean getOnBoardingFlag(){
        return sharedPreferences.getBoolean(Consts.ON_BOARDING_FLAG, false);
    }
    public void setOnBoardingFlag(boolean value){
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(Consts.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(Consts.ON_BOARDING_FLAG, value);
        editor.apply();
    }
}
