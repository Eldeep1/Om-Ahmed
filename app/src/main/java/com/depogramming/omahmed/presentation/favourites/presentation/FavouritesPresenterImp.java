package com.depogramming.omahmed.presentation.favourites.presentation;

import android.content.Context;
import android.os.Bundle;

import com.depogramming.omahmed.R;
import com.depogramming.omahmed.data.meals.model.meal.FavouriteMeals;
import com.depogramming.omahmed.data.meals.model.utils.MealMapper;
import com.depogramming.omahmed.data.meals.repository.MealsRepo;
import com.depogramming.omahmed.presentation.favourites.view.FavouritesView;
import com.depogramming.omahmed.presentation.favourites.view.OnCardClicked;
import com.depogramming.omahmed.presentation.favourites.view.OnHeartClicked;
import com.depogramming.omahmed.utils.ActionCheckingDialogue;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavouritesPresenterImp implements FavouritesPresenter {

    private FavouritesView favouritesView;
    private OnHeartClicked onHeartClicked;
    private OnCardClicked onItemClicked;
    MealsRepo mealsRepo;
    private final CompositeDisposable disposables = new CompositeDisposable();


    public FavouritesPresenterImp(Context context) {
        mealsRepo = new MealsRepo(context);
    }

    @Override
    public void getAllFavourites() {
        favouritesView.favouritesLoading();
        disposables.add(mealsRepo.getFavouriteMeals().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        favouritesView::favouritesGotSuccessfully,
                        throwable -> favouritesView.favouritesFailed(throwable.getMessage())
                ));
    }

    @Override
    public void changeFavouritesFavState(Context context,FavouriteMeals favouriteMeals, int position) {
        ActionCheckingDialogue.show(
                context,
                "Remove from favourites",
                "Are you sure you want to remove this delicious recipe from your favourites",
                R.drawable.broken_heart,
                result -> {
                    if (result) {
                        disposables.add(mealsRepo.removeFavourite(favouriteMeals).subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(
                                        () -> onHeartClicked.removeFavouriteUI(position, "Removed Successfully"),
                                        throwable -> favouritesView.favouritesFailed(throwable.getMessage())
                                ));
                    }
                }
                );

    }

    @Override
    public void onCardClicked(FavouriteMeals favouriteMeals) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("meal", MealMapper.toMeal(favouriteMeals));
        onItemClicked.onCardClickedAction(bundle);
    }

    @Override
    public void clear() {
        disposables.clear();
        favouritesView = null;
        onHeartClicked = null;
        onItemClicked = null;
    }

    @Override
    public void setView(FavouritesView favouritesView, OnHeartClicked onHeartClicked, OnCardClicked onCardClicked) {
        this.favouritesView = favouritesView;
        this.onHeartClicked=onHeartClicked;
        this.onItemClicked=onCardClicked;
    }
}
