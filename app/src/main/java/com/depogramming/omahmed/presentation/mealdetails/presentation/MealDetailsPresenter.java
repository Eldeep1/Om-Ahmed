package com.depogramming.omahmed.presentation.mealdetails.presentation;

import android.content.Context;

import com.depogramming.omahmed.data.home.models.Meal;

import java.util.Date;

public interface MealDetailsPresenter {
    void getMealIngredients();
    void toggleFavourite(Context context);

    void addToPlanner(Date date, Context context);
    void backButton();
}
