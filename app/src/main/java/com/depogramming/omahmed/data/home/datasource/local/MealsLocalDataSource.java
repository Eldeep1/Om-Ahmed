package com.depogramming.omahmed.data.home.datasource.local;

import android.content.Context;

import com.depogramming.omahmed.data.db.AppDataBase;
import com.depogramming.omahmed.data.home.models.FavouriteMeals;
import com.depogramming.omahmed.data.home.models.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

public class MealsLocalDataSource {
    private final MealsDao mealsDao;

    public MealsLocalDataSource(Context context) {
        mealsDao = AppDataBase.getInstance(context).mealsDao();
    }

    public Observable<List<FavouriteMeals>> getAllMeals() {
        return mealsDao.getFavourites();
    }

    public Completable insertMeal(FavouriteMeals meal) {
        return mealsDao.addToFav(meal);
    }

    public Completable deleteMeal(FavouriteMeals meal) {
        return mealsDao.deleteMeal(meal);
    }
}
