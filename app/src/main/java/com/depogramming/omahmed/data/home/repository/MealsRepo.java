package com.depogramming.omahmed.data.home.repository;

import com.depogramming.omahmed.data.home.datasource.remote.MealsRemoteDataSource;
import com.depogramming.omahmed.data.home.models.Meal;
import com.depogramming.omahmed.data.home.models.MealsResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class MealsRepo {
    MealsRemoteDataSource mealsRemoteDataSource;
    public MealsRepo(){
        mealsRemoteDataSource=new MealsRemoteDataSource();
    }
    public Observable<List<Meal>> searchForMealsByFirstChar(char c){
        return mealsRemoteDataSource.searchForMealsByFirstChar(c).map(MealsResponse::getMeals);
    }
    public Observable<List<Meal>> getDailyRecommendations(List<Character> chars) {
        return Observable.zip(
                searchForMealsByFirstChar(chars.get(0)),
                searchForMealsByFirstChar(chars.get(1)),
                (list1, list2) -> {
                    List<Meal> combined = new ArrayList<>();
                    if (list1 != null) combined.addAll(list1);
                    if (list2 != null) combined.addAll(list2);
                    return combined;
                }
        );
    }
    public Observable<Meal> getDailyMeal() {
        return mealsRemoteDataSource.getRandomMeal().map(mealsResponse -> mealsResponse.getMeals().get(0));
    }

}
