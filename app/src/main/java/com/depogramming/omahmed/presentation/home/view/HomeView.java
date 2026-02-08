package com.depogramming.omahmed.presentation.home.view;

import com.depogramming.omahmed.data.home.models.Meal;

public interface HomeView extends CategoriesView, RecommendationMeals, DailyMeal{
    void updateDailyMealFavState(boolean isFav);

    void networkError();

    void updateListViewHeart(int position, boolean isFavourite);
}
