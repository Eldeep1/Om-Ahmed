package com.depogramming.omahmed.presentation.home.presenter;

import android.view.View;

import com.depogramming.omahmed.data.home.models.Meal;

public interface HomePresenter {
    void getAllCategories();
    void getDailyRecommendations();
    void getDailyMeal();
    void retryAllButton();
    void changeFavState(Meal meal);
}
