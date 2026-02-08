package com.depogramming.omahmed.presentation.home.view;

import com.depogramming.omahmed.data.home.models.Meal;

import java.util.List;

public interface DailyMeal {
    void dailyMealLoading();
    void dailyMealSuccessfully(Meal meal);
    void dailyMealFailed(String errorMessage);
}
