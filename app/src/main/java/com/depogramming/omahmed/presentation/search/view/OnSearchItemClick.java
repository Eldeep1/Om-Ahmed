package com.depogramming.omahmed.presentation.search.view;

import android.os.Bundle;

import com.depogramming.omahmed.data.meals.model.meal.Meal;

public interface OnSearchItemClick {
    void onCardClicked(Meal meal);
    void navigateToMealDetails(Bundle bundle);
    void onHeartClicked(Meal meal, int position);
    void onHeartClickedAction(Meal meal, int position);
}
