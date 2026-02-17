package com.depogramming.omahmed.presentation.home.presenter;

import android.content.Context;

import com.depogramming.omahmed.data.meals.model.meal.Meal;
import com.depogramming.omahmed.presentation.home.view.HomeView;

public interface HomePresenter {
    void retryAllButton();
    void changeDailyMealFavState(Meal meal,Context context);
    void changeRecommendationsFavState(Meal meal, int position, Context context);
    void navigateToMealDetails(Meal meal);
    void clear();
    void navigateToSearch(String category);
    void setView(HomeView homeView);
    void initData();
}
