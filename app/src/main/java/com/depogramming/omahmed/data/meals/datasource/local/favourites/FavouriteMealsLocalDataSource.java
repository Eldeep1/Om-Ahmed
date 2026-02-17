package com.depogramming.omahmed.data.meals.datasource.local.favourites;

import android.content.Context;

import com.depogramming.omahmed.data.db.AppDataBase;
import com.depogramming.omahmed.data.meals.model.meal.FavouriteMeals;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

public class FavouriteMealsLocalDataSource {
    private final FavouritesMealsDao mealsDao;

    public FavouriteMealsLocalDataSource(Context context) {
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
    public Completable insertAll(List<FavouriteMeals> meals) {
        return mealsDao.insertAll(meals);
    }
}
