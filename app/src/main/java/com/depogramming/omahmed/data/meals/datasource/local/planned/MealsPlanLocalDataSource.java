package com.depogramming.omahmed.data.meals.datasource.local.planned;

import android.content.Context;

import com.depogramming.omahmed.data.db.AppDataBase;
import com.depogramming.omahmed.data.meals.model.meal.MealsPlanModel;

import java.util.Date;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

public class MealsPlanLocalDataSource {
    private final MealsPlanDao mealsPlanDao;

    public MealsPlanLocalDataSource(Context context) {
        mealsPlanDao = AppDataBase.getInstance(context).mealsPlanDao();
    }

    public Observable<List<MealsPlanModel>> getDayPlans(Date startOfDay, Date endOfDay) {
        return mealsPlanDao.getPlanByDateRange(startOfDay.getTime(),endOfDay.getTime());
    }

    public Completable insertPlanned(MealsPlanModel meal) {
        return mealsPlanDao.addToPlan(meal);
    }

    public Completable deletePlannedMeal(MealsPlanModel meal) {
        return mealsPlanDao.removeFromPlan(meal);
    }

    public Observable<List<MealsPlanModel>> getAllPlannedMeals(){
        return mealsPlanDao.getAllPlannedMeals();
    }
    public Completable insertAll(List<MealsPlanModel> plans) {
        return mealsPlanDao.insertAll(plans);
    }

}
