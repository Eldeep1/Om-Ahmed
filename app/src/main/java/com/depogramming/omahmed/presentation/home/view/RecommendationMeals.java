package com.depogramming.omahmed.presentation.home.view;

import com.depogramming.omahmed.data.home.models.Meal;

import java.util.List;

public interface RecommendationMeals {
    void recommendationMealsLoading();
    void recommendationsMealsSuccessful(List<Meal> meals);
    void recommendationsMealsFailed(String errorMessage);
}
