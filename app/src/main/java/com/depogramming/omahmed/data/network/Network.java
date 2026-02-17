package com.depogramming.omahmed.data.network;


import com.depogramming.omahmed.data.meals.datasource.remote.MealsService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Network {
    private static Network instance = null;
    private MealsService mealsService;
    private final Retrofit retrofit;
    private Network() { // Private constructor
        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.themealdb.com/api/json/v1/1/") // Added https://
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }

    public static synchronized Network getInstance() {
        if (instance == null) {
            instance = new Network();
        }
        return instance;
    }
    public MealsService getMealsService(){
        if(mealsService==null){
            mealsService=retrofit.create(MealsService.class);
        }
        return mealsService;
    }
}
