package com.depogramming.omahmed.presentation.favourites.view;

import android.os.Bundle;

import com.depogramming.omahmed.data.home.models.FavouriteMeals;

public interface OnCardClicked {
    void onCardClicked(FavouriteMeals meal);
    void onCardClickedAction(Bundle bundle);
}
