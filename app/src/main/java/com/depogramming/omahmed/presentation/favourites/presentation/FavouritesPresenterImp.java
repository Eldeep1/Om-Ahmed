package com.depogramming.omahmed.presentation.favourites.presentation;

import android.content.Context;
import android.os.Bundle;

import com.depogramming.omahmed.data.home.models.FavouriteMeals;
import com.depogramming.omahmed.data.home.models.MealMapper;
import com.depogramming.omahmed.data.home.repository.MealsRepo;
import com.depogramming.omahmed.presentation.favourites.view.FavouritesView;
import com.depogramming.omahmed.presentation.favourites.view.OnCardClicked;
import com.depogramming.omahmed.presentation.favourites.view.OnHeartClicked;

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
    public void changeFavouritesFavState(FavouriteMeals favouriteMeals, int position) {
        disposables.add(mealsRepo.removeFavourite(favouriteMeals).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> onHeartClicked.removeFavouriteUI(position),
                        throwable -> favouritesView.favouritesFailed(throwable.getMessage())
                ));
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
