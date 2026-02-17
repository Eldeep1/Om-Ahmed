package com.depogramming.omahmed.presentation.mealdetails.view;

import com.depogramming.omahmed.data.meals.model.meal.Meal;

public interface MealDetailsView {
    void showMealIngredients(Meal meal);
    void toggleFavouriteButton(boolean isFav, String message);
    void addToPlannerSuccess();
    void backButtonClicked();
}
