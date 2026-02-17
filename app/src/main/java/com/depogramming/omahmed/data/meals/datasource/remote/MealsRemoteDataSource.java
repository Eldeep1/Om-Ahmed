package com.depogramming.omahmed.data.meals.datasource.remote;

import com.depogramming.omahmed.data.meals.model.areas.AreasResponse;
import com.depogramming.omahmed.data.meals.model.categories.CategoriesResponse;
import com.depogramming.omahmed.data.meals.model.meal.MealsResponse;
import com.depogramming.omahmed.data.network.Network;

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
    public Observable<AreasResponse> getAreas(){
        return mealsService.getAreasNames();
    }
    public Observable<CategoriesResponse> getAllCategories(){
        return mealsService.getAllCategories();
    }

}
