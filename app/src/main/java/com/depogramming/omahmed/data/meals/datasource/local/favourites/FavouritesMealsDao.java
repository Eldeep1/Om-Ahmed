package com.depogramming.omahmed.data.meals.datasource.local.favourites;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.depogramming.omahmed.data.meals.model.meal.FavouriteMeals;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

@Dao
public interface FavouritesMealsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable addToFav(FavouriteMeals meal);

    @Query("SELECT * FROM Favourites where favouriteFlag=1")
    Observable<List<FavouriteMeals>> getFavourites();
    @Delete
    Completable deleteMeal(FavouriteMeals meal);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertAll(List<FavouriteMeals> meals);
}
