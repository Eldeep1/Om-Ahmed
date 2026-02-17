package com.depogramming.omahmed.presentation.favourites.view;

import com.depogramming.omahmed.data.meals.model.meal.FavouriteMeals;

public interface OnHeartClicked {
    public void removeFavouriteLogic(FavouriteMeals favouriteMeals, int position);
    public void removeFavouriteUI(int position, String message);
}
