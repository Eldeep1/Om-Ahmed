package com.depogramming.omahmed.data.mealsplan.datasource.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.depogramming.omahmed.data.mealsplan.models.MealsPlanModel;

import java.util.Date;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

@Dao
public interface MealsPlanDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable addToPlan(MealsPlanModel meal);
    @Delete
    Completable removeFromPlan(MealsPlanModel meal);
    @Query("SELECT * FROM plan WHERE date >= :startTimestamp AND date < :endTimestamp")
    Observable<List<MealsPlanModel>> getPlanByDateRange(long startTimestamp, long endTimestamp);
    @Query("SELECT * FROM plan")
    Observable<List<MealsPlanModel>> getAllPlannedMeals();
}
