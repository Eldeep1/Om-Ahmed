package com.depogramming.omahmed.presentation.home.view;

import com.depogramming.omahmed.data.meals.model.meal.Meal;

public interface OnItemClick {
    void onHeartClicked(Meal meal, int position);
    void onCardClicked(Meal meal);
}
