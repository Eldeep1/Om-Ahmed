package com.depogramming.omahmed.data.home.datasource.remote;

import com.depogramming.omahmed.data.home.models.MealsResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MealsService {
    @GET("search.php/")
    Observable<MealsResponse> searchForMealsByFirstChar(@Query("f")char firstLetter);

     @GET("random.php/")
    Observable<MealsResponse> randomMeal();
}
