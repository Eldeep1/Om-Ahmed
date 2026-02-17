package com.depogramming.omahmed.presentation.planner.presentation;

import android.content.Context;

import com.depogramming.omahmed.data.mealsplan.models.CalendarDay;
import com.depogramming.omahmed.data.mealsplan.models.MealsPlanModel;
import com.depogramming.omahmed.presentation.planner.view.CalenderView;
import com.depogramming.omahmed.presentation.planner.view.OnPlannedMealClick;

public interface PlannerPresenter {
    void nextMonth();
    void previousMonth();
    void loadMealsForDay(CalendarDay day, int position);
    void removeFromPlanned(Context context, MealsPlanModel meal, int position);
    void clear();
    void navigateToDetails(MealsPlanModel meal);

    void init(CalenderView calenderView, OnPlannedMealClick onPlannedMealClick);
}
