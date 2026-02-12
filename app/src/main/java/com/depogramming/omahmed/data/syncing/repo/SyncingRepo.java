package com.depogramming.omahmed.data.syncing.repo;

import android.content.Context;
import android.util.Pair;
import com.depogramming.omahmed.data.home.datasource.local.MealsLocalDataSource;
import com.depogramming.omahmed.data.home.models.FavouriteMeals;
import com.depogramming.omahmed.data.mealsplan.datasource.local.MealsPlanLocalDataSource;
import com.depogramming.omahmed.data.mealsplan.models.MealsPlanModel;
import com.depogramming.omahmed.data.syncing.datasource.FireStoreDataSource;


import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SyncingRepo {
    FireStoreDataSource fireStoreDataSource;
    MealsLocalDataSource mealsLocalDataSource;
    MealsPlanLocalDataSource mealsPlanLocalDataSource;
    public SyncingRepo(Context context) {
        fireStoreDataSource= new FireStoreDataSource();
        mealsLocalDataSource= new MealsLocalDataSource(context);
        mealsPlanLocalDataSource= new MealsPlanLocalDataSource(context);
    }

    public Completable uploadAllUsersData() {
        return Single.zip(
                        mealsLocalDataSource.getAllMeals().firstOrError(),
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
                                mealsLocalDataSource.insertAll(pair.second)
                                        .subscribeOn(Schedulers.io()),
                                mealsPlanLocalDataSource.insertAll(pair.first)
                                        .subscribeOn(Schedulers.io())
                        )
                )
                .subscribeOn(Schedulers.io());
    }




}
