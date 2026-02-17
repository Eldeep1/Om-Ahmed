package com.depogramming.omahmed.presentation.home.view;

import com.depogramming.omahmed.data.meals.model.meal.Meal;

import java.util.List;

public interface RecommendationMeals {
    void recommendationsMealsSuccessful(List<Meal> meals);
}
