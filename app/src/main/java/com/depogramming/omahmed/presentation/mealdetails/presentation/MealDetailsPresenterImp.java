package com.depogramming.omahmed.presentation.mealdetails.presentation;

import android.content.Context;

import com.depogramming.omahmed.data.home.datasource.local.MealsLocalDataSource;
import com.depogramming.omahmed.data.home.models.Meal;
import com.depogramming.omahmed.data.home.repository.MealsRepo;
import com.depogramming.omahmed.presentation.mealdetails.view.MealDetailsView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealDetailsPresenterImp implements MealDetailsPresenter{
    private final MealDetailsView view;
    MealsRepo mealsRepo;
    Meal meal;
    public MealDetailsPresenterImp(MealDetailsView view, Meal meal, Context context) {
        this.view = view;
        this.meal=meal;
        mealsRepo = new MealsRepo(context);
    }



    @Override
    public void getMealIngredients() {
        view.showMealIngredients(meal);
    }


    @Override
    public void toggleFavourite() {
        Disposable subscribe = mealsRepo.toggleFavourite(meal)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    meal.isFav = !meal.isFav;
                    view.toggleFavouriteButton(meal.isFav);
                });
    }
}
