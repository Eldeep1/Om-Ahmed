package com.depogramming.omahmed.presentation.search.view;

import com.depogramming.omahmed.data.home.models.Category;
import com.depogramming.omahmed.data.home.models.CountryModel;
import com.depogramming.omahmed.data.home.models.Meal;

import java.util.List;

public interface SearchViewInterface {
    void showMeals(List<Meal> meals);
    void showCategories(List<Category> categories);
    void showCountries(List<CountryModel> countries);
}
