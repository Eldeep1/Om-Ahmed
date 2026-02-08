package com.depogramming.omahmed.presentation.mealdetails.presentation;

import com.depogramming.omahmed.data.home.models.Meal;
import com.depogramming.omahmed.presentation.mealdetails.view.MealDetailsView;

public class MealDetailsPresenterImp implements MealDetailsPresenter{
    private final MealDetailsView view;
    Meal meal;
    public MealDetailsPresenterImp(MealDetailsView view, Meal meal) {
        this.view = view;
        this.meal=meal;
    }



    @Override
    public void getMealIngredients() {
        view.showMealIngredients(meal);
    }

    @Override
    public void getMealInstructions() {

    }

    @Override
    public void getMealVideo() {

    }

    @Override
    public void toggleFavourite(Meal meal) {

    }
}
