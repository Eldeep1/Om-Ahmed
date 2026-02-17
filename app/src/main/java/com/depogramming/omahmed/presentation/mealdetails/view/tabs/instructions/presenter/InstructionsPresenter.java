package com.depogramming.omahmed.presentation.mealdetails.view.tabs.instructions.presenter;

import com.depogramming.omahmed.data.meals.model.meal.Meal;

public interface InstructionsPresenter {
    void loadMealDetails(Meal meal);
    void clear();

}
