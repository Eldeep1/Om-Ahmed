package com.depogramming.omahmed.data.network;


import com.depogramming.omahmed.data.home.datasource.remote.AreasService;
import com.depogramming.omahmed.data.home.datasource.remote.CategoriesService;
import com.depogramming.omahmed.data.home.datasource.remote.MealsService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Network {
    private static Network instance = null;
    private CategoriesService categoryService;
    private MealsService mealsService;
    private AreasService areasService;
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

    public CategoriesService getCategoryService() {
        if(categoryService==null){
            categoryService=retrofit.create(CategoriesService.class);
        }
        return categoryService;
    }
    public MealsService getMealsService(){
        if(mealsService==null){
            mealsService=retrofit.create(MealsService.class);
        }
        return mealsService;
    }
    public AreasService getAreasService(){
        if(areasService==null){
            areasService=retrofit.create(AreasService.class);
        }
        return areasService;
    }
}
