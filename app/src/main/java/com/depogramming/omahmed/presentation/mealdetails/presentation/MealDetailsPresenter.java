package com.depogramming.omahmed.presentation.mealdetails.presentation;

import com.depogramming.omahmed.data.home.models.Meal;

import java.util.Date;

public interface MealDetailsPresenter {
    void getMealIngredients();
    void toggleFavourite();

    void addToPlanner(Date date);
}
