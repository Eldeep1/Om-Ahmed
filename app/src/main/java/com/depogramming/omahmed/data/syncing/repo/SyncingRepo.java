package com.depogramming.omahmed.data.syncing.repo;

import android.content.Context;
import android.util.Pair;
import com.depogramming.omahmed.data.meals.datasource.local.favourites.FavouriteMealsLocalDataSource;
import com.depogramming.omahmed.data.meals.datasource.local.planned.MealsPlanLocalDataSource;
import com.depogramming.omahmed.data.syncing.datasource.FireStoreDataSource;


import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SyncingRepo {
    FireStoreDataSource fireStoreDataSource;
    FavouriteMealsLocalDataSource favouriteMealsLocalDataSource;
    MealsPlanLocalDataSource mealsPlanLocalDataSource;
    public SyncingRepo(Context context) {
        fireStoreDataSource= new FireStoreDataSource();
        favouriteMealsLocalDataSource = new FavouriteMealsLocalDataSource(context);
        mealsPlanLocalDataSource= new MealsPlanLocalDataSource(context);
    }

    public Completable uploadAllUsersData() {
        return Single.zip(
                        favouriteMealsLocalDataSource.getAllMeals().firstOrError(),
                        mealsPlanLocalDataSource.getAllPlannedMeals().firstOrError(),
                        Pair::new
                )
                .subscribeOn(Schedulers.io())
                .flatMapCompletable(pair ->
                        fireStoreDataSource.uploadAllUsersData(
                                pair.second, // plans
                                pair.first   // favourites
                        )
                );
    }
    public Completable downloadAllUsersData() {
        return fireStoreDataSource.downloadAllUsersData()
                .flatMapCompletable(pair ->
                        Completable.mergeArray(
                                favouriteMealsLocalDataSource.insertAll(pair.second)
                                        .subscribeOn(Schedulers.io()),
                                mealsPlanLocalDataSource.insertAll(pair.first)
                                        .subscribeOn(Schedulers.io())
                        )
                )
                .subscribeOn(Schedulers.io());
    }




}
