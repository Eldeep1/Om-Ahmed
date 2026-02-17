package com.depogramming.omahmed.presentation.planner.view;

import android.os.Bundle;

import com.depogramming.omahmed.data.meals.model.meal.MealsPlanModel;

public interface OnPlannedMealClick {
    void onRemoveButtonClicked(MealsPlanModel meal, int position);
    void onCardClicked(MealsPlanModel meal);
    void onRemoveButtonAction(MealsPlanModel meal, int positon,String message);
    void onCardClickedAction(Bundle bundle);
}
