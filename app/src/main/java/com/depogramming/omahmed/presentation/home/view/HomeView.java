package com.depogramming.omahmed.presentation.home.view;

import android.os.Bundle;

public interface HomeView extends CategoriesView, RecommendationMeals, DailyMeal{
    void updateDailyMealFavState(boolean isFav,String message);
    void updateListViewHeart(int position, boolean isFavourite, String message);

    void navigateToMealDetails(Bundle bundle);
    void allMealsLoading();
    void allMealsSuccessfully();
    void allMealsError();

}
