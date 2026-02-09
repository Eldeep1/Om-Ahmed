package com.depogramming.omahmed.presentation.mealdetails.view;

import com.depogramming.omahmed.data.home.models.Meal;

public interface MealDetailsView {
    void showMealIngredients(Meal meal);
    void toggleFavouriteButton(boolean isFav);

}
