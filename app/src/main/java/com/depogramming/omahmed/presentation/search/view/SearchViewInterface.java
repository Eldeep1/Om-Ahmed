package com.depogramming.omahmed.presentation.search.view;

import com.depogramming.omahmed.data.meals.model.categories.Category;
import com.depogramming.omahmed.data.meals.model.countries.CountryModel;
import com.depogramming.omahmed.data.meals.model.meal.Meal;

import java.util.List;

public interface SearchViewInterface {
    void showMeals(List<Meal> meals);
    void showCategories(List<Category> categories);
    void showCountries(List<CountryModel> countries);
    void setMeals(List<Meal> meals);
}
