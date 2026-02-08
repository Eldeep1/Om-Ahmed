package com.depogramming.omahmed.data.home.repository;

import android.content.Context;

import com.depogramming.omahmed.data.home.datasource.local.MealsLocalDataSource;
import com.depogramming.omahmed.data.home.datasource.remote.MealsRemoteDataSource;
import com.depogramming.omahmed.data.home.models.FavouriteMeals;
import com.depogramming.omahmed.data.home.models.Meal;
import com.depogramming.omahmed.data.home.models.MealMapper;
import com.depogramming.omahmed.data.home.models.MealsResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

public class MealsRepo {
    MealsRemoteDataSource mealsRemoteDataSource;
    MealsLocalDataSource mealsLocalDataSource;
    public MealsRepo(Context context){
        mealsRemoteDataSource=new MealsRemoteDataSource();
        mealsLocalDataSource=new MealsLocalDataSource(context);
    }
    public Observable<List<Meal>> searchForMealsByFirstChar(char c) {
        return Observable.combineLatest(
                mealsRemoteDataSource
                        .searchForMealsByFirstChar(c)
                        .map(MealsResponse::getMeals),

                mealsLocalDataSource.getAllMeals(),

                this::applyFavourites
        );
    }

    public Observable<List<Meal>> getDailyRecommendations(List<Character> chars) {
        Observable<List<Meal>> remoteMeals =
                Observable.zip(
                        searchForMealsByFirstChar(chars.get(0)),
                        searchForMealsByFirstChar(chars.get(1)),
                        (list1, list2) -> {
                            List<Meal> combined = new ArrayList<>();
                            if (list1 != null) combined.addAll(list1);
                            if (list2 != null) combined.addAll(list2);
                            return combined;
                        }
                );

        return Observable.combineLatest(
                remoteMeals,
                mealsLocalDataSource.getAllMeals(),
                this::applyFavourites
        );
    }

    public Observable<Meal> getDailyMeal() {
        return Observable.combineLatest(
                mealsRemoteDataSource
                        .getRandomMeal()
                        .map(r -> r.getMeals().get(0)),

                mealsLocalDataSource.getAllMeals(),

                (meal, favourites) -> {
                    for (FavouriteMeals fav : favourites) {
                        if (meal.idMeal.equals(fav.idMeal)) {
                            meal.isFav = true;
                            break;
                        }
                    }
                    return meal;
                }
        );
    }

    private List<Meal> applyFavourites(
            List<Meal> meals,
            List<FavouriteMeals> favourites
    ) {
        if (meals == null || favourites == null) return meals;

        for (Meal meal : meals) {
            for (FavouriteMeals fav : favourites) {
                if (meal.idMeal.equals(fav.idMeal)) {
                    meal.isFav = true;
                    break;
                }
            }
        }
        return meals;
    }

    public Completable toggleFavourite(Meal meal) {
        if (meal.isFav) {
            return mealsLocalDataSource.deleteMeal(MealMapper.toFavourite(meal));
        } else {
            return mealsLocalDataSource.insertMeal(MealMapper.toFavourite(meal));
        }
    }

    public Observable<List<FavouriteMeals>> getFavouriteMeals(){
        return mealsLocalDataSource.getAllMeals();
    }
    public Completable removeFavourite(FavouriteMeals favouriteMeals){
        return mealsLocalDataSource.deleteMeal(favouriteMeals);
    }
}
