package com.depogramming.omahmed.presentation.mealdetails.presentation;
import com.depogramming.omahmed.data.home.models.MealMapper;
import com.depogramming.omahmed.data.mealsplan.models.MealsPlanModel;
import com.depogramming.omahmed.data.mealsplan.repo.MealsPlanRepo;
import com.google.android.material.datepicker.MaterialDatePicker;

import android.content.Context;

import com.depogramming.omahmed.data.home.datasource.local.MealsLocalDataSource;
import com.depogramming.omahmed.data.home.models.Meal;
import com.depogramming.omahmed.data.home.repository.MealsRepo;
import com.depogramming.omahmed.presentation.mealdetails.view.MealDetailsView;

import java.util.Date;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealDetailsPresenterImp implements MealDetailsPresenter{
    private final MealDetailsView view;
    private final MealsPlanRepo mealsPlanRepo;
    private final MealsRepo mealsRepo;
    Meal meal;
    public MealDetailsPresenterImp(MealDetailsView view, Meal meal, Context context) {
        this.view = view;
        this.meal=meal;
        mealsRepo = new MealsRepo(context);
        mealsPlanRepo = new MealsPlanRepo(context);
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

    @Override
    public void addToPlanner(Date date) {
    //call meals repo
        //meal repo calls local data source
        // local data source calls room
        //room creates a table named plan
        // the table stores the needed data from the API and the selected date

        MealsPlanModel mealPlan = MealMapper.toMealPlanner(meal, date);

        mealsPlanRepo.insertPlanned(mealPlan)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                            view.addToPlannerSuccess();
                        });

// In your Fragment or Activity
    }
}
