package com.depogramming.omahmed.presentation.home.view;

import com.depogramming.omahmed.data.home.models.Meal;

import java.util.List;

public interface MealsView {
    void mealsLoading();
    void mealsGotSuccessfully(List<Meal> meals);
    void mealsFailed(String errorMessage);
}
