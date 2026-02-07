package com.depogramming.omahmed.data.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.depogramming.omahmed.data.home.datasource.local.MealsDao;
import com.depogramming.omahmed.data.home.models.FavouriteMeals;


@Database(entities = {FavouriteMeals.class},version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract MealsDao mealsDao();
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
