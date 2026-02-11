package com.depogramming.omahmed.presentation.search.presentation;

import com.depogramming.omahmed.data.home.models.Meal;

public interface SearchPresenter {
    void getSearchMeals(String selectedCountry, String selectedCategory);
    void getAreas();
    void getCategories();
    void navigateToMealDetails(Meal meal);

    void toggleFavourite(Meal meal, int position);
}
