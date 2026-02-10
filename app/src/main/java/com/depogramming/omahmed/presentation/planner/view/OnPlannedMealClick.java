package com.depogramming.omahmed.presentation.planner.view;

import com.depogramming.omahmed.data.home.models.Meal;
import com.depogramming.omahmed.data.mealsplan.models.MealsPlanModel;

public interface OnPlannedMealClick {
    void onRemoveButtonClicked(MealsPlanModel meal, int position);
    void onCardClicked(MealsPlanModel meal);
    void onRemoveButtonAction(MealsPlanModel meal, int positon);
    void onCardClickedAction(Meal meal);
}
