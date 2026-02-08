package com.depogramming.omahmed.presentation.favourites.presentation;

import android.content.Context;

import com.depogramming.omahmed.data.home.models.FavouriteMeals;
import com.depogramming.omahmed.data.home.repository.MealsRepo;
import com.depogramming.omahmed.presentation.favourites.view.FavouritesView;
import com.depogramming.omahmed.presentation.favourites.view.OnHeartClicked;

import java.util.Collections;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavouritesPresenterImp implements FavouritesPresenter {

    private final FavouritesView favouritesView;
    private final OnHeartClicked onHeartClicked;
    MealsRepo mealsRepo;

    public FavouritesPresenterImp(Context context, FavouritesView favouritesView, OnHeartClicked onHeartClicked) {
        this.favouritesView = favouritesView;
        this.onHeartClicked = onHeartClicked;
        mealsRepo=new MealsRepo(context);
    }
    @Override
    public void getAllFavourites() {
        favouritesView.favouritesLoading();
        Disposable subscribe = mealsRepo.getFavouriteMeals().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        favouritesView::favouritesGotSuccessfully,
                        throwable -> favouritesView.favouritesFailed(throwable.getMessage())
                );
    }

    @Override
    public void changeFavouritesFavState(FavouriteMeals favouriteMeals, int position) {
        Disposable subscribe = mealsRepo.removeFavourite(favouriteMeals).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> onHeartClicked.removeFavouriteUI(position),
                        throwable -> favouritesView.favouritesFailed(throwable.getMessage())
                );
    }
}
