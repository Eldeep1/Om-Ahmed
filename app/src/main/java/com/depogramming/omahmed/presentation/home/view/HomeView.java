package com.depogramming.omahmed.presentation.home.view;

import android.os.Bundle;

import com.depogramming.omahmed.data.home.models.Meal;

public interface HomeView extends CategoriesView, RecommendationMeals, DailyMeal{
    void updateDailyMealFavState(boolean isFav);

    void networkError();

    void updateListViewHeart(int position, boolean isFavourite);

    void navigateToMealDetails(Bundle bundle);
    void allMealsLoading();
    void allMealsSuccessfully();
    void allMealsError();
}
