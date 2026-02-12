package com.depogramming.omahmed.presentation.home.presenter;

import android.content.Context;
import android.view.View;

import com.depogramming.omahmed.data.home.models.Meal;

public interface HomePresenter {
    void getAllCategories();
    void getDailyRecommendations();
    void getDailyMeal();
    void retryAllButton();
    void changeDailyMealFavState(Meal meal,Context context);
    void changeRecommendationsFavState(Meal meal, int position, Context context);
    void navigateToMealDetails(Meal meal);

    void navigateToSearch(String category);
}
