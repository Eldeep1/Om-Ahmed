package com.depogramming.omahmed.presentation.mealdetails.presentation;

import com.depogramming.omahmed.data.meals.model.utils.MealMapper;
import com.depogramming.omahmed.data.meals.model.meal.MealsPlanModel;
import com.depogramming.omahmed.utils.FavouriteToggleHelper;
import com.depogramming.omahmed.utils.GuestModeDialog;
import com.depogramming.omahmed.utils.UserData;

import android.content.Context;

import com.depogramming.omahmed.data.meals.model.meal.Meal;
import com.depogramming.omahmed.data.meals.repository.MealsRepo;
import com.depogramming.omahmed.presentation.mealdetails.view.MealDetailsView;

import java.util.Date;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MealDetailsPresenterImp implements MealDetailsPresenter {
    private MealDetailsView view;
    private final MealsRepo mealsRepo;
    Meal meal;
    private final CompositeDisposable disposables = new CompositeDisposable();

    public MealDetailsPresenterImp(Context context) {
        mealsRepo = new MealsRepo(context);
    }


    @Override
    public void getMealIngredients() {
        view.showMealIngredients(meal);
    }


    @Override
    public void toggleFavourite(Context context) {
        FavouriteToggleHelper.toggle(
                context,meal,mealsRepo,disposables,(isFav, message) -> view.toggleFavouriteButton(isFav, message)
        );
    }

    @Override
    public void addToPlanner(Date date, Context context) {
        if (UserData.isGuest) {
            GuestModeDialog.show(context);
        } else {
            MealsPlanModel mealPlan = MealMapper.toMealPlanner(meal, date);
            disposables.add(mealsRepo.insertPlanned(mealPlan)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(view::addToPlannerSuccess));
        }

    }

    @Override
    public void backButton() {
        view.backButtonClicked();
    }

    @Override
    public void clear() {
        disposables.clear();
        view = null;
    }

    @Override
    public void setView(Meal meal, MealDetailsView view) {
        this.view = view;
        this.meal = meal;
    }
}
