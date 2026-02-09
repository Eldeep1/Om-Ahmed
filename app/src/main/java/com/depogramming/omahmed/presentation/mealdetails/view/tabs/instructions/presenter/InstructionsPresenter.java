package com.depogramming.omahmed.presentation.mealdetails.view.tabs.instructions.presenter;

import com.depogramming.omahmed.data.home.models.Meal;

public interface InstructionsPresenter {
    void loadMealDetails(Meal meal);
    void clear();

}
