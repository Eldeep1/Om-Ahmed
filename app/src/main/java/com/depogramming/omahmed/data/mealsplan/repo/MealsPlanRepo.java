package com.depogramming.omahmed.data.mealsplan.repo;

import android.content.Context;

import com.depogramming.omahmed.data.mealsplan.datasource.local.MealsPlanLocalDataSource;
import com.depogramming.omahmed.data.mealsplan.models.MealsPlanModel;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

public class MealsPlanRepo {
    MealsPlanLocalDataSource mealsPlanLocalDataSource;

    public MealsPlanRepo(Context context){
        mealsPlanLocalDataSource=new MealsPlanLocalDataSource(context);
    }
    public Observable<List<MealsPlanModel>> getAllPlannedMeals(){
        return mealsPlanLocalDataSource.getAllPlannedMeals();
    }
    public Completable insertPlanned(MealsPlanModel meal){
        return mealsPlanLocalDataSource.insertPlanned(meal);
    }
    public Completable deletePlannedMeal(MealsPlanModel meal){
        return mealsPlanLocalDataSource.deletePlannedMeal(meal);
    }
}
