package com.depogramming.omahmed.data.home.datasource.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.depogramming.omahmed.data.home.models.FavouriteMeals;
import com.depogramming.omahmed.data.home.models.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

@Dao
public interface MealsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable addToFav(FavouriteMeals meal);

    @Query("SELECT * FROM Favourites where favouriteFlag=1")
    Observable<List<FavouriteMeals>> getFavourites();
    @Delete
    Completable deleteMeal(FavouriteMeals meal);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertAll(List<FavouriteMeals> meals);
}
