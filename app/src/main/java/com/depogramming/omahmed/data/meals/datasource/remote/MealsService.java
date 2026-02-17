package com.depogramming.omahmed.data.meals.datasource.remote;

import com.depogramming.omahmed.data.meals.model.areas.AreasResponse;
import com.depogramming.omahmed.data.meals.model.categories.CategoriesResponse;
import com.depogramming.omahmed.data.meals.model.meal.MealsResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealsService {
    @GET("search.php/")
    Observable<MealsResponse> searchForMealsByFirstChar(@Query("f")char firstLetter);

     @GET("random.php/")
    Observable<MealsResponse> randomMeal();
    @GET("list.php?a=list/")
    Observable<AreasResponse> getAreasNames();
    @GET("categories.php/")
    Observable<CategoriesResponse> getAllCategories();
}
