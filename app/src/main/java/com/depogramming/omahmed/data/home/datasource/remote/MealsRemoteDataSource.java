package com.depogramming.omahmed.data.home.datasource.remote;

import com.depogramming.omahmed.data.home.models.Meal;
import com.depogramming.omahmed.data.home.models.MealsResponse;
import com.depogramming.omahmed.data.network.Network;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class MealsRemoteDataSource {
    MealsService mealsService;

    public MealsRemoteDataSource() {
        mealsService= Network.getInstance().getMealsService();
    }
    public Observable<MealsResponse> searchForMealsByFirstChar(char c){
        return mealsService.searchForMealsByFirstChar(c);
    }
    public Observable<MealsResponse> getRandomMeal(){
        return mealsService.randomMeal();
    }

}
