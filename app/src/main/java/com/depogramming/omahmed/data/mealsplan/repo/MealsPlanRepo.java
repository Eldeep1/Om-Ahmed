package com.depogramming.omahmed.data.mealsplan.repo;

import android.content.Context;

import com.depogramming.omahmed.data.mealsplan.datasource.local.MealsPlanLocalDataSource;
import com.depogramming.omahmed.data.mealsplan.models.MealsPlanModel;
import com.depogramming.omahmed.data.utils.DateUtils;

import java.util.Date;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

public class MealsPlanRepo {
    MealsPlanLocalDataSource mealsPlanLocalDataSource;

    public MealsPlanRepo(Context context){
        mealsPlanLocalDataSource=new MealsPlanLocalDataSource(context);
    }
    public Observable<List<MealsPlanModel>> getAllPlannedMeals(Date day){
        Date startOfDay = DateUtils.getStartOfDay(day);
        Date endOfDay = DateUtils.getEndOfDay(day);
        return mealsPlanLocalDataSource.getDayPlans(startOfDay,endOfDay);
    }
    public Completable insertPlanned(MealsPlanModel meal){
        return mealsPlanLocalDataSource.insertPlanned(meal);
    }
    public Completable deletePlannedMeal(MealsPlanModel meal){
        return mealsPlanLocalDataSource.deletePlannedMeal(meal);
    }
}
