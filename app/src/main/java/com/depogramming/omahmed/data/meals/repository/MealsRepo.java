package com.depogramming.omahmed.data.meals.repository;

import android.content.Context;

import com.depogramming.omahmed.data.meals.datasource.local.favourites.FavouriteMealsLocalDataSource;
import com.depogramming.omahmed.data.meals.datasource.remote.MealsRemoteDataSource;
import com.depogramming.omahmed.data.meals.model.areas.Areas;
import com.depogramming.omahmed.data.meals.model.categories.CategoriesResponse;
import com.depogramming.omahmed.data.meals.model.meal.FavouriteMeals;
import com.depogramming.omahmed.data.meals.model.meal.Meal;
import com.depogramming.omahmed.data.meals.model.utils.MealMapper;
import com.depogramming.omahmed.data.meals.model.meal.MealsResponse;
import com.depogramming.omahmed.data.meals.datasource.local.planned.MealsPlanLocalDataSource;
import com.depogramming.omahmed.data.meals.model.meal.MealsPlanModel;
import com.depogramming.omahmed.data.meals.model.utils.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

public class MealsRepo {
    MealsRemoteDataSource mealsRemoteDataSource;
    FavouriteMealsLocalDataSource favouriteMealsLocalDataSource;
    MealsPlanLocalDataSource mealsPlanLocalDataSource;

    public MealsRepo(Context context){
        mealsRemoteDataSource=new MealsRemoteDataSource();
        favouriteMealsLocalDataSource =new FavouriteMealsLocalDataSource(context);
        mealsPlanLocalDataSource=new MealsPlanLocalDataSource(context);
    }
    public Observable<List<Meal>> getAllMeals() {

        return Observable.range('a', 26)
                .map(i -> (char) i.intValue())
                .flatMap(this::searchForMealsByFirstChar)
                .map(response ->
                        response != null
                                ? response
                                : new ArrayList<>()
                ); // emit Meal one by one
    }

    public Observable<List<Meal>> searchForMealsByFirstChar(char c) {
        return Observable.combineLatest(
                mealsRemoteDataSource
                        .searchForMealsByFirstChar(c)
                        .map(MealsResponse::getMeals),

                favouriteMealsLocalDataSource.getAllMeals(),

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
                favouriteMealsLocalDataSource.getAllMeals(),
                this::applyFavourites
        );
    }

    public Observable<Meal> getDailyMeal(char c) {
        return Observable.combineLatest(
                mealsRemoteDataSource
                        .searchForMealsByFirstChar(c)
                        .map(r -> r.getMeals().get(0)),

                favouriteMealsLocalDataSource.getAllMeals(),

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
            return favouriteMealsLocalDataSource.deleteMeal(MealMapper.toFavourite(meal));
        } else {
            return favouriteMealsLocalDataSource.insertMeal(MealMapper.toFavourite(meal));
        }
    }

    public Observable<List<FavouriteMeals>> getFavouriteMeals(){
        return favouriteMealsLocalDataSource.getAllMeals();
    }
    public Completable removeFavourite(FavouriteMeals favouriteMeals){
        return favouriteMealsLocalDataSource.deleteMeal(favouriteMeals);
    }

    public Observable<CategoriesResponse> getAllCategories(){
        return mealsRemoteDataSource.getAllCategories();
    }
    public Observable<List<Areas>> getAreas(){
        return mealsRemoteDataSource.getAreas().map(areasResponse -> areasResponse.areas);
    }

    public Observable<List<MealsPlanModel>> getAllPlannedMeals(Date day){
        Date startOfDay = DateUtils.getStartOfDay(day);
        Date endOfDay = DateUtils.getEndOfDay(day);
        return mealsPlanLocalDataSource.getDayPlans(startOfDay,endOfDay);
    }
    public Completable insertPlanned(MealsPlanModel meal){
        return mealsPlanLocalDataSource.insertPlanned(meal);
    }
    public Completable deletePlannedMeal(MealsPlanModel meal){
        return mealsPlanLocalDataSource.deletePlannedMeal(meal);
    }
}
