package com.depogramming.omahmed.presentation.favourites.presentation;

import com.depogramming.omahmed.data.home.models.FavouriteMeals;
import com.depogramming.omahmed.presentation.favourites.view.FavouritesView;
import com.depogramming.omahmed.presentation.favourites.view.OnCardClicked;
import com.depogramming.omahmed.presentation.favourites.view.OnHeartClicked;

import java.util.List;

public interface FavouritesPresenter {
    void getAllFavourites();
    void changeFavouritesFavState(FavouriteMeals favouriteMeals, int position);
    void onCardClicked(FavouriteMeals favouriteMeals);
    public void clear();

    void setView(FavouritesView favouritesView, OnHeartClicked onHeartClicked, OnCardClicked onCardClicked);
}
