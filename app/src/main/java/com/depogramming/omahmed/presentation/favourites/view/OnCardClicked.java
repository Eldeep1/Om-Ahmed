package com.depogramming.omahmed.presentation.favourites.view;

import android.os.Bundle;

import com.depogramming.omahmed.data.meals.model.meal.FavouriteMeals;

public interface OnCardClicked {
    void onCardClicked(FavouriteMeals meal);
    void onCardClickedAction(Bundle bundle);
}
