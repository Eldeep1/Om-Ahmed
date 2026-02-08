package com.depogramming.omahmed.presentation.favourites.presentation;

import com.depogramming.omahmed.data.home.models.FavouriteMeals;

import java.util.List;

public interface FavouritesPresenter {
    void getAllFavourites();
    void changeFavouritesFavState(FavouriteMeals favouriteMeals, int position);

}
