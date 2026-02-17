package com.depogramming.omahmed.presentation.favourites.presentation;

import android.content.Context;

import com.depogramming.omahmed.data.meals.model.meal.FavouriteMeals;
import com.depogramming.omahmed.presentation.favourites.view.FavouritesView;
import com.depogramming.omahmed.presentation.favourites.view.OnCardClicked;
import com.depogramming.omahmed.presentation.favourites.view.OnHeartClicked;

public interface FavouritesPresenter {
    void getAllFavourites();
    void changeFavouritesFavState(Context context,FavouriteMeals favouriteMeals, int position);
    void onCardClicked(FavouriteMeals favouriteMeals);
    public void clear();

    void setView(FavouritesView favouritesView, OnHeartClicked onHeartClicked, OnCardClicked onCardClicked);
}
