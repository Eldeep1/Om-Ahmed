package com.depogramming.omahmed.presentation.favourites.view;

import com.depogramming.omahmed.data.meals.model.meal.FavouriteMeals;

import java.util.List;

public interface FavouritesView {
    void favouritesLoading();

    void favouritesGotSuccessfully(List<FavouriteMeals> favouriteMeals);

    void favouritesFailed(String errorMessage);

}
