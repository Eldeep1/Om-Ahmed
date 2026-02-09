package com.depogramming.omahmed.data.mealsplan.datasource.local;

import android.content.Context;

import com.depogramming.omahmed.data.db.AppDataBase;
import com.depogramming.omahmed.data.mealsplan.models.MealsPlanModel;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

public class MealsPlanLocalDataSource {
    private final MealsPlanDao mealsPlanDao;

    public MealsPlanLocalDataSource(Context context) {
        mealsPlanDao = AppDataBase.getInstance(context).mealsPlanDao();
    }

    public Observable<List<MealsPlanModel>> getAllPlannedMeals() {
        return mealsPlanDao.getPlan();
    }

    public Completable insertPlanned(MealsPlanModel meal) {
        return mealsPlanDao.addToPlan(meal);
    }

    public Completable deletePlannedMeal(MealsPlanModel meal) {
        return mealsPlanDao.removeFromPlan(meal);
    }
}
