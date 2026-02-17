package com.depogramming.omahmed.presentation.search.presentation;

import android.content.Context;

import com.depogramming.omahmed.data.meals.model.meal.Meal;
import com.depogramming.omahmed.presentation.search.view.OnSearchItemClick;
import com.depogramming.omahmed.presentation.search.view.SearchViewInterface;

public interface SearchPresenter {
    void getSearchMeals(String selectedCountry, String selectedCategory);
    void getAreas();
    void getCategories();
    void navigateToMealDetails(Meal meal);
    void toggleFavourite(Meal meal, int position, Context context);
    void searchBySpecificMeal(String query, String selectedCountry, String selectedCategory);
    void clear();

    void setViews(OnSearchItemClick onSearchItemClick, SearchViewInterface searchView);
}
