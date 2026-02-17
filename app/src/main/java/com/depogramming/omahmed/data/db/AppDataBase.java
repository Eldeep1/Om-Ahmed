package com.depogramming.omahmed.data.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.depogramming.omahmed.data.meals.datasource.local.favourites.FavouritesMealsDao;
import com.depogramming.omahmed.data.meals.model.meal.FavouriteMeals;
import com.depogramming.omahmed.data.meals.datasource.local.planned.MealsPlanDao;
import com.depogramming.omahmed.data.meals.model.meal.MealsPlanModel;


@Database(entities = {FavouriteMeals.class, MealsPlanModel.class},version = 1,exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    public abstract FavouritesMealsDao mealsDao();
    public abstract MealsPlanDao mealsPlanDao();
    private static AppDataBase Instance;
    public static AppDataBase getInstance(Context context){
        if(Instance==null){
            Instance= Room.databaseBuilder(
                    context,AppDataBase.class,"MealsDB"
            ).build();
        }
        return Instance;
    }
}
